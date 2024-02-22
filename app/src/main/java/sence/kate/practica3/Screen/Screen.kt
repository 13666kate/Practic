package sence.kate.practica3.Screen

sealed class Screen {
    object LoginScreen : Screen()
    object RegistrationScreen : Screen()
    object UserListScreen : Screen()
}