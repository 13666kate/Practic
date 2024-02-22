package sence.kate.practica3.Screen.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import sence.kate.practica3.MVVM.DataStoreViewModel
import sence.kate.practica3.R
import sence.kate.practica3.RandomI.RandomImage
import sence.kate.practica3.padding.Padding
import sence.kate.practica3.prefdatastore.DataStoreManager
import sence.kate.practica3.ui.theme.ItemUsers

@Composable
fun HomeScreen(
    dataStoreViewModel: DataStoreViewModel,
    onTreeButtonClick: () -> Unit,
    dataStoreManager: DataStoreManager
) {
    Column(
        modifier = Modifier.fillMaxSize(),

        horizontalAlignment = Alignment.Start
    ) {
        Button(
            modifier = Modifier
                .width(150.dp)
                .padding(Padding.paddingNormal)
                .height(80.dp),
            colors = ButtonDefaults.buttonColors(Color.Black),
            onClick = {
                onTreeButtonClick()
            }
        ) {
            Text(text = stringResource(R.string.Back))
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black),
        ) {
            itemsIndexed(
                dataStoreViewModel.listUsers(
                    dataStoreManager = dataStoreManager,
                    image = RandomImage().randomImageResId()
                )
            ) { index, user ->
                ItemUsers(user)
                dataStoreViewModel.name.value = ""
            }
        }
    }
}