package com.example.whatsapp.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.whatsapp.Presentation.CallScreen.CallScreen
import com.example.whatsapp.Presentation.CommunityScreen.CommunityScreen
import com.example.whatsapp.Presentation.HomeScreen.HomeScreen
import com.example.whatsapp.Presentation.SplashScreen.SplashScreen
import com.example.whatsapp.Presentation.UpdateScreen.UpdateScreen
import com.example.whatsapp.Presentation.UserRegistrationScreen.UserRegistrationScreen
import com.example.whatsapp.Presentation.WelcomeScreen.WelcomeScreen

@Composable
fun WhatsAppNavigationSystem()
{
    val navController = rememberNavController()
    NavHost(startDestination = Routes.SplashScreen, navController = navController) {
        composable<Routes.SplashScreen> {
            SplashScreen(navController)
        }
        composable<Routes.WelcomeScreen> {
            WelcomeScreen(navController)
        }
        composable<Routes.UserRegistrationScreen> {
            UserRegistrationScreen(navController)
        }
        composable<Routes.HomeScreen> {
            HomeScreen(navController)
        }
        composable<Routes.UpdateScreen> {
            UpdateScreen(navController)
        }
        composable<Routes.CommunityScreen> {
            CommunityScreen(navController)
        }
        composable<Routes.CallScreen> {
            CallScreen(navController)
        }
    }
}