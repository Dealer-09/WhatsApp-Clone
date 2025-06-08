package com.example.whatsapp.Presentation.viewmodels

import android.app.Activity
import android.util.Log
import android.content.Context
import android.graphics.Bitmap
import android.util.Base64
import androidx.lifecycle.ViewModel
import com.example.whatsapp.Model.PhoneAuthUser
import com.google.firebase.Firebase
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.database.FirebaseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit
import javax.inject.Inject


@HiltViewModel
class PhoneAuthViewModel @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val database: FirebaseDatabase) :
    ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState = _authState.asStateFlow()

    private val userRef = database.reference.child("users")

    fun sendVerificationCode(phoneNumber: String, activity: Activity) {
        if (phoneNumber.isEmpty()) {
            _authState.value = AuthState.Error("Please enter a phone number")
            return
        }

        _authState.value = AuthState.Loading

        val option = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onCodeSent(id: String, token: PhoneAuthProvider.ForceResendingToken) {
                super.onCodeSent(id, token)
                Log.d("PhoneAuth", "onCodeSent triggered. verification ID: $id")
                _authState.value = AuthState.CodeSent(verificationId = id)
            }

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d("PhoneAuth", "Verification completed automatically")
                signWithCredential(credential, context = activity)
            }

            override fun onVerificationFailed(exception: FirebaseException) {
                Log.e("PhoneAuth", "Verification failed: ${exception.message}")
                _authState.value = AuthState.Error(exception.message ?: "Verification failed")
            }
        }

        try {
            val phoneAuthOptions = PhoneAuthOptions.newBuilder(firebaseAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(activity)
                .setCallbacks(option)
                .build()

            PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)        } catch (e: Exception) {
            Log.e("PhoneAuth", "Error starting verification: ${e.message}")
            _authState.value = AuthState.Error("Failed to start verification")
        }
    }

    private fun signWithCredential(credential: PhoneAuthCredential, context: Context) {
        _authState.value = AuthState.Loading

        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val phoneAuthUser = PhoneAuthUser(
                        userId = user?.uid ?: "",
                        phoneNumber = user?.phoneNumber ?: ""
                    )
                    markUserAsSignedIn(context)
                    _authState.value = AuthState.Success(phoneAuthUser)

                    fetchUserProfile(user?.uid ?: "")
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Sign-in failed")                }
            }
            .addOnFailureListener { exception ->
                _authState.value = AuthState.Error(exception.message ?: "Authentication failed")
            }
    }

    fun markUserAsSignedIn(context: Context) {
        val sharedPreference = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        sharedPreference.edit().putBoolean("is_signed_in", true).apply()
    }

    fun fetchUserProfile(userId: String) {
        val userRef = userRef.child(userId)
        userRef.get().addOnSuccessListener { snapshot ->
            if (snapshot.exists()) {
                val userProfile = snapshot.getValue(PhoneAuthUser::class.java)
                if (userProfile != null) {
                    _authState.value = AuthState.Success(userProfile)
                }
            }
        }.addOnFailureListener {
            _authState.value = AuthState.Error("Failed to fetch user profile")
        }
    }

    fun verifyCode(otp: String, context: Context) {
        val currentAuthState = _authState.value

        if (currentAuthState !is AuthState.CodeSent || currentAuthState.verificationId.isEmpty()) {
            Log.e("PhoneAuth", "Attempting to verify OTP without valid verification ID")
            _authState.value = AuthState.Error("Invalid verification ID")
            return
        }

        if (otp.isEmpty() || otp.length != 6) {
            _authState.value = AuthState.Error("Please enter a valid 6-digit OTP")
            return
        }

        try {
            val credential = PhoneAuthProvider.getCredential(currentAuthState.verificationId, otp)
            signWithCredential(credential, context)        } catch (e: Exception) {
            Log.e("PhoneAuth", "Error creating credential: ${e.message}")
            _authState.value = AuthState.Error("Invalid OTP format")
        }
    }

    fun saveUserProfile(userId: String, name: String, status: String, profileImage: Bitmap?) {
        val database = FirebaseDatabase.getInstance().reference
        val encodedImage = profileImage?.let { convertBitmapToBase64(it) }
        val userProfile = PhoneAuthUser(
            userId = userId,
            name = name,
            status = status,
            phoneNumber = Firebase.auth.currentUser?.phoneNumber ?: "",
            profileImage = encodedImage
        )

        database.child("users").child(userId).setValue(userProfile)
            .addOnSuccessListener {
                Log.d("PhoneAuth", "User profile saved successfully")
            }
            .addOnFailureListener { exception ->
                Log.e("PhoneAuth", "Failed to save user profile: ${exception.message}")
                _authState.value = AuthState.Error("Failed to save user profile")
            }
    }

    private fun convertBitmapToBase64(bitmap: Bitmap): String {        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val bytesArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(bytesArray, Base64.DEFAULT)
    }

    fun resetAuthState() {
        _authState.value = AuthState.Idle
    }

    fun signOut(activity: Activity) {
        firebaseAuth.signOut()
        val sharedPreference = activity.getSharedPreferences("app_prefs", Activity.MODE_PRIVATE)
        sharedPreference.edit().putBoolean("is_signed_in", false).apply()
        _authState.value = AuthState.Idle
    }
}

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class CodeSent(val verificationId: String) : AuthState()
    data class Success(val user: PhoneAuthUser) : AuthState()
    data class Error(val message: String) : AuthState()
}