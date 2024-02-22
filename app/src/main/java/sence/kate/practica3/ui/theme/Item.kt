package sence.kate.practica3.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import sence.kate.practica3.DataClasses.Friend
import sence.kate.practica3.R
import sence.kate.practica3.padding.Padding


@Composable
fun ItemUsers(friend: Friend) {
   
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(Padding.paddingNormal)
            .clip(RoundedCornerShape(8.dp)),

    ) {
        Image(
            painter = painterResource(id = friend.image),
            contentDescription = "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(Padding.border)
                .size(Padding.inageSize)
                .clip(CircleShape)
        )
        Text(
            text = friend.text,
            fontSize =30.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.padding(Padding.paddingSmall),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.black)

        )
    }

}
