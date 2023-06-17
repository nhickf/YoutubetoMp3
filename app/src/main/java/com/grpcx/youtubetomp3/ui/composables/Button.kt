package com.grpcx.youtubetomp3.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun PreviewButton() {
    LoadingButton()
}

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    buttonText : String = "Download",
    enabled : Boolean = true,
    onClick: () -> Unit = {}
) {
    ElevatedButton(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        Text(text = buttonText)
    }
}

@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    enabled : Boolean = true,
    onClick: () -> Unit = {}
) {
    ElevatedButton(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick,
        enabled = enabled
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(24.dp),
                strokeWidth = 2.dp
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                textAlign = TextAlign.Center,
                text = "Grabbing Info..."
            )
        }
    }
}

