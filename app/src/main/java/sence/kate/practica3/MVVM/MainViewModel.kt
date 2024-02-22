package sence.kate.practica3.MVVM


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import sence.kate.practica3.Screen.Screen

class MainViewModel : ViewModel() {

    var currentScreen by mutableStateOf<Screen>(Screen.LoginScreen)
        private set
    var passwordScreen1 = mutableStateOf("")

    var passwordVisibleScreen2 = mutableStateOf(false)
        private set

    var colorBlack: MutableState<Color> = mutableStateOf(Color.Black)
    var textColor: MutableState<Color> = mutableStateOf(Color.White)
    var loginScreen1 = mutableStateOf("")

    fun onPasswordVisibilityToggle(passwordVisible: MutableState<Boolean>) {
        passwordVisible.value = !passwordVisible.value
    }

    companion object {
        fun currentScreen(mainViewModel: MainViewModel, screen: Screen) {
            mainViewModel.currentScreen = screen
        }
    }
}