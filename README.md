# 📱 WhatsApp Clone

A modern Android WhatsApp clone application built with Jetpack Compose, featuring real-time messaging, status updates, voice calls, and community features.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Firebase](https://img.shields.io/badge/firebase-%23039BE5.svg?style=for-the-badge&logo=firebase)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=jetpackcompose&logoColor=white)

## ✨ Features

### 🔐 Authentication
- **Phone Number Authentication** with Firebase Auth
- **OTP Verification** for secure login
- **Country Code Selection** with dropdown
- **Auto Sign-in** persistence

### 💬 Messaging
- **Chat Interface** with modern Material 3 design
- **Real-time Messaging** powered by Firebase Realtime Database
- **Chat List** with last message preview
- **Message Timestamps** and status indicators

### 📸 Status Updates
- **WhatsApp Stories** equivalent feature
- **Status Viewing** with time stamps
- **Channel Following** system
- **My Status** with add functionality

### 📞 Voice Calls
- **Call History** with missed call indicators
- **Favorite Contacts** horizontal scrolling
- **Call Interface** with proper call states
- **Recent Calls** management

### 👥 Communities
- **Community Groups** functionality
- **Member Count** display
- **Create New Community** feature
- **Community Search** capabilities

### 🎨 UI/UX
- **Material 3 Design** with WhatsApp color scheme
- **Bottom Navigation** with 4 main sections
- **Floating Action Buttons** for quick actions
- **Search Functionality** across all sections
- **Responsive Design** for different screen sizes

## 🛠️ Tech Stack

### **Frontend**
- **Kotlin** - Primary programming language
- **Jetpack Compose** - Modern UI toolkit
- **Material 3** - Design system
- **Navigation Compose** - Type-safe navigation

### **Backend & Database**
- **Firebase Authentication** - Phone number auth
- **Firebase Realtime Database** - Real-time data sync
- **Firebase Services** - Backend infrastructure

### **Architecture & Patterns**
- **MVVM Architecture** - Clean separation of concerns
- **Dependency Injection** - Hilt for DI
- **Repository Pattern** - Data layer abstraction
- **StateFlow** - Reactive state management

### **Development Tools**
- **Android Studio** - IDE
- **Gradle KTS** - Build system
- **Kotlin Serialization** - Type-safe navigation
- **Hilt** - Dependency injection framework

## 📁 Project Structure

```
app/src/main/java/com/example/whatsapp/
├── 📁 DI/                          # Dependency Injection
│   └── AppModule.kt                # Hilt modules
├── 📁 Model/                       # Data models
│   └── PhoneAuthUser.kt           # User data model
├── 📁 Presentation/               # UI Layer
│   ├── 📁 BottomNavigation/       # Bottom nav component
│   ├── 📁 CallScreen/             # Call-related screens
│   ├── 📁 CommunityScreen/        # Community features
│   ├── 📁 HomeScreen/             # Chat interface
│   ├── 📁 Navigation/             # App navigation
│   ├── 📁 SplashScreen/           # App launch screen
│   ├── 📁 UpdateScreen/           # Status updates
│   ├── 📁 UserRegistrationScreen/ # Auth screens
│   ├── 📁 viewmodeles/            # ViewModels
│   └── 📁 WelcomeScreen/          # Welcome screen
├── 📁 ui/theme/                   # App theming
└── MainActivity.kt                # Main activity
```

## 🚀 Getting Started

### Prerequisites

- **Android Studio** Arctic Fox or newer
- **JDK 11** or higher
- **Android SDK** with minimum API level 24
- **Firebase Project** set up

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/your-username/WhatsAppClone.git
   cd WhatsAppClone
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory

3. **Firebase Setup**
   - Create a new Firebase project at [Firebase Console](https://console.firebase.google.com/)
   - Add your Android app to the Firebase project
   - Download `google-services.json` and place it in the `app/` directory
   - Enable **Authentication** → **Phone** sign-in method
   - Set up **Realtime Database** with appropriate rules

4. **Sync and Build**
   ```bash
   ./gradlew clean build
   ```

5. **Run the app**
   - Connect an Android device or start an emulator
   - Click "Run" in Android Studio or use:
   ```bash
   ./gradlew installDebug
   ```

## 🔧 Configuration

### Firebase Configuration

1. **Authentication Rules**
   - Enable Phone authentication in Firebase Console
   - Configure your SHA-1 fingerprint for debugging

2. **Database Rules** (for development)
   ```json
   {
     "rules": {
       ".read": "auth != null",
       ".write": "auth != null"
     }
   }
   ```

### Build Configuration

The project is configured with:
- **compileSdk**: 35
- **minSdk**: 24
- **targetSdk**: 35
- **Kotlin**: 2.0.21
- **Compose BOM**: 2024.09.00

## 📱 Screenshots

*Add screenshots of your app here showing different screens*

## 🎯 Roadmap

- [ ] **Real-time Messaging** - Complete chat functionality
- [ ] **Voice Messages** - Audio recording and playback
- [ ] **Media Sharing** - Image and video sharing
- [ ] **Group Chats** - Multi-user conversations
- [ ] **Video Calls** - Video calling feature
- [ ] **End-to-End Encryption** - Message encryption
- [ ] **Push Notifications** - Real-time notifications
- [ ] **Dark Mode** - Theme switching
- [ ] **Message Search** - Search across conversations
- [ ] **Backup & Restore** - Data backup functionality

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch (`git checkout -b feature/AmazingFeature`)
3. **Commit** your changes (`git commit -m 'Add some AmazingFeature'`)
4. **Push** to the branch (`git push origin feature/AmazingFeature`)
5. **Open** a Pull Request

### Development Guidelines

- Follow **Kotlin coding conventions**
- Use **Jetpack Compose** for all UI components
- Implement proper **error handling**
- Add **unit tests** for business logic
- Update documentation for new features

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👨‍💻 Author

**Your Name**
- GitHub: [@your-username](https://github.com/your-username)
- LinkedIn: [Your LinkedIn](https://linkedin.com/in/your-profile)
- Email: your.email@example.com

## 🙏 Acknowledgments

- **WhatsApp** for the design inspiration
- **Firebase** for backend services
- **Jetpack Compose** team for the amazing UI toolkit
- **Material Design** for the design system
- **Android community** for continuous support

## 📞 Support

If you have any questions or need help getting started, please:

1. Check the [Issues](https://github.com/your-username/WhatsAppClone/issues) page
2. Create a new issue if your problem isn't already reported
3. Reach out via email for urgent matters

---

**Made with ❤️ using Jetpack Compose**

*This is a clone project for educational purposes. WhatsApp is a trademark of Meta Platforms, Inc.*
