package com.grpcx.youtubetomp3.ui.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewText() {
    DefaultText(text = "Hello")
}


@Composable
fun DefaultText(
    modifier: Modifier = Modifier,
    text : String,
) {
    Text(text = text)
}

@Composable
fun TextIcon(
    modifier: Modifier = Modifier,
    iconModifier : Modifier = Modifier,
    text : String,
    @DrawableRes icon : Int,
) {

    Row (
        modifier = Modifier
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        
        Image(
            modifier = iconModifier,
            painter = painterResource(id = icon),
            contentDescription = "text-icon"
        )
        
        Text(
            modifier = modifier,
            text = text,
        )
    }
}