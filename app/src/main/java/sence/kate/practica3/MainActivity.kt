package sence.kate.practica3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import sence.kate.practica3.MVVM.DataStoreViewModel
import sence.kate.practica3.MVVM.MainViewModel
import sence.kate.practica3.Screen.Screen
import sence.kate.practica3.prefdatastore.DataStoreManager
import sence.kate.practica3.Screen.screens.HomeScreen
import sence.kate.practica3.Screen.screens.LoginScreen
import sence.kate.practica3.ui.theme.Practica3Theme
import sence.kate.practica3.Screen.screens.RegistrationScreen

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private val dataStoreViewModel by viewModels<DataStoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Получаем значение логина как состояние Compose
        super.onCreate(savedInstanceState)
        setContent {
            val dataStoreManager = DataStoreManager(this)
            Practica3Theme {

                val painter: Painter = painterResource(R.drawable.baseline_visibility_24)

                // A surface container using the 'background' color from the theme
                val yesVisibility: Painter = painterResource(R.drawable.baseline_visibility_24)
                val noVisibility: Painter = painterResource(R.drawable.baseline_visibility_off_24)

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (viewModel.currentScreen) {
                        is Screen.LoginScreen -> LoginScreen(
                            viewModel,
                            onNavigateBack = {
                                MainViewModel.currentScreen(
                                    viewModel,
                                    Screen.RegistrationScreen
                                )
                            },
                            onSecondButtonClick = {
                                MainViewModel.currentScreen(
                                    viewModel,
                                    Screen.UserListScreen
                                )
                            },
                            dataStoreManager = dataStoreManager,
                        )

                        is Screen.RegistrationScreen -> HomeScreen(
                            dataStoreViewModel = dataStoreViewModel,
                            dataStoreManager = dataStoreManager,
                            onTreeButtonClick = {
                                MainViewModel.currentScreen(
                                    viewModel,
                                    Screen.LoginScreen
                                )
                            }
                        )

                        is Screen.UserListScreen -> {
                            RegistrationScreen(
                                dataStoreViewModel = dataStoreViewModel,
                                onNavigateBack = {
                                    MainViewModel.currentScreen(
                                        viewModel,
                                        Screen.LoginScreen
                                    )
                                },
                                dataStoreManager = dataStoreManager,
                                viewModel = viewModel,
                                yesVisibility = yesVisibility,
                                noVisibility = noVisibility
                            )
                        }
                    }
                }
            }
        }
    }
}






