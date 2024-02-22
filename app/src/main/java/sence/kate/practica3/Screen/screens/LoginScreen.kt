package sence.kate.practica3.Screen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.launch
import sence.kate.practica3.MVVM.MainViewModel
import sence.kate.practica3.R
import sence.kate.practica3.padding.Padding
import sence.kate.practica3.prefdatastore.DataStoreManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: MainViewModel,
    onNavigateBack: () -> Unit,
    dataStoreManager: DataStoreManager,
    onSecondButtonClick: () -> Unit,
) {
    val scope = rememberCoroutineScope()
    Image(
        painter = painterResource(id = R.drawable.phon), contentDescription = "phon",
        Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            viewModel.loginScreen1.value,
            onValueChange = { newLogin ->
                viewModel.loginScreen1.value = newLogin
            },
            singleLine = true,
            modifier = Modifier
                .padding(Padding.paddingNormal)
                .width(Padding.widthUtilScreen1),
            label = {
                Text(
                    text = stringResource(R.string.login),
                    color = viewModel.textColor.value,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = viewModel.textColor.value,
                focusedBorderColor = viewModel.colorBlack.value,
                focusedLabelColor = viewModel.colorBlack.value,
                cursorColor = viewModel.colorBlack.value
            ),
        )
        OutlinedTextField(
            viewModel.passwordScreen1.value,
            onValueChange = { newPassword ->
                viewModel.passwordScreen1.value = newPassword
            },
            label = {
                Text(
                    text = stringResource(R.string.password),
                    color = viewModel.textColor.value,
                    style = TextStyle(fontWeight = FontWeight.Bold),

                    )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = viewModel.textColor.value,
                focusedBorderColor = viewModel.colorBlack.value,
                cursorColor = viewModel.colorBlack.value
            ),
            singleLine = true,
            modifier = Modifier.width(Padding.widthUtilScreen1)

        )

        Button(
            modifier = Modifier
                .width(Padding.widthButtonScreen1)
                .padding(Padding.paddingSmall, Padding.paddingSmall, Padding.paddingSmall),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                onNavigateBack()

            }
        ) {
            Text(text = stringResource(R.string.Login))
        }
        Button(
            modifier = Modifier
                .width(Padding.widthButtonScreen1)
                .padding(Padding.paddingSmall, Padding.paddingZero, Padding.paddingSmall),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                onSecondButtonClick()
            }
        ) {
            Text(text = stringResource(R.string.registration))
        }
        Button(
            modifier = Modifier
                .width(Padding.widthButtonScreen1)
                .padding(Padding.paddingSmall, Padding.paddingZero, Padding.paddingSmall),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                scope.launch {
                    dataStoreManager.clearData();
                }
            }
        ) {
            Text(text = ("Очистить "))
        }
    }
}