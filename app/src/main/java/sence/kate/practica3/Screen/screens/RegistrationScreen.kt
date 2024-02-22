package sence.kate.practica3.Screen.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import kotlinx.coroutines.launch
import sence.kate.practica3.MVVM.DataStoreViewModel
import sence.kate.practica3.MVVM.MainViewModel
import sence.kate.practica3.R
import sence.kate.practica3.padding.Padding
import sence.kate.practica3.prefdatastore.DataStoreManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    dataStoreViewModel: DataStoreViewModel,
    onNavigateBack: () -> Unit,
    dataStoreManager: DataStoreManager,
    viewModel: MainViewModel,
    yesVisibility: Painter,
    noVisibility: Painter,
) {
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val scope1 = rememberCoroutineScope()
    dataStoreViewModel.saveUserNameToDataStore(
        dataStoreViewModel.name.value,
        dataStoreManager
    )
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
            dataStoreViewModel.name.value,
            onValueChange = { newLogin ->
                dataStoreViewModel.name.value = newLogin
            },
            singleLine = true,
            modifier = Modifier
                .width(Padding.widthUtilScreen1),
            label = {
                Text(
                    text = stringResource(R.string.name),
                    color = viewModel.textColor.value,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
            },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = viewModel.textColor.value,
                focusedBorderColor = viewModel.colorBlack.value,
                focusedLabelColor = viewModel.colorBlack.value,
                cursorColor = viewModel.colorBlack.value
            ),
        )
        OutlinedTextField(
            dataStoreViewModel.login.value,
            onValueChange = { newLogin ->
                dataStoreViewModel.login.value = newLogin
            },
            singleLine = true,
            modifier = Modifier
                .width(Padding.widthUtilScreen1),
            label = {
                Text(
                    text = stringResource(R.string.login),
                    color = viewModel.textColor.value,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
            },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = viewModel.textColor.value,
                focusedBorderColor = viewModel.colorBlack.value,
                focusedLabelColor = viewModel.colorBlack.value,
                cursorColor = viewModel.colorBlack.value
            ),
        )

        OutlinedTextField(
            dataStoreViewModel.password.value,
            onValueChange = { newPassword ->
                dataStoreViewModel.password.value = newPassword
            },
            label = {
                Text(
                    text = stringResource(R.string.password),
                    color = viewModel.textColor.value,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                )
            },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = viewModel.textColor.value,
                focusedBorderColor = viewModel.colorBlack.value,
                focusedLabelColor = viewModel.colorBlack.value,
                cursorColor = viewModel.colorBlack.value,
            ),
            visualTransformation = if (viewModel.passwordVisibleScreen2.value)
                VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                var image = if (viewModel.passwordVisibleScreen2.value) {
                    yesVisibility
                } else {
                    noVisibility
                }
                IconButton(onClick = { viewModel.onPasswordVisibilityToggle(viewModel.passwordVisibleScreen2) }) {
                    Icon(painter = image, contentDescription = "Visability")
                }
            },
            singleLine = true,
            modifier = Modifier.width(Padding.widthUtilScreen1)
        )
        Button(
            modifier = Modifier
                .width(Padding.widthButtonScreen1)
                .padding(Padding.paddingSmall, Padding.paddingSmall, Padding.paddingSmall),
            colors = ButtonDefaults.buttonColors(Color.Black),

            onClick = {
                scope.launch {
                    dataStoreViewModel.saveInDataStoreManager(dataStoreManager)
                    dataStoreViewModel.observeUserNameFromDataStore(dataStoreManager)
                    dataStoreViewModel.observeLoginFromDataStore(dataStoreManager)
                    dataStoreViewModel.observePasswordFromDataStore(dataStoreManager)
                }
                onNavigateBack()

            }
        ) {
            Text(text = stringResource(R.string.registration))
        }
    }
}