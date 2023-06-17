package com.grpcx.youtubetomp3.ui.screen.initial

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.grpcx.youtubetomp3.R
import com.grpcx.youtubetomp3.data.AppState
import com.grpcx.youtubetomp3.data.Status
import com.grpcx.youtubetomp3.ui.composables.DefaultButton
import com.grpcx.youtubetomp3.ui.composables.DefaultEditText
import com.grpcx.youtubetomp3.ui.composables.DefaultText
import com.grpcx.youtubetomp3.ui.composables.DestinationSelector
import com.grpcx.youtubetomp3.ui.composables.LoadingButton
import com.grpcx.youtubetomp3.ui.composables.TextIcon


@Preview(showBackground = true)
@Composable
fun DefaultScreen() {
    InitialScreen(AppState.InitialState())
}

@Composable
fun InitialScreen(
    state: AppState.InitialState,
    onUrlChange: (String) -> Unit = {},
    onFileSelectorClicked: () -> Unit = {},
    onGrabVideoInfo: () -> Unit = {},
) {

    Column(
        modifier = Modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(17.dp)
    ) {

        DefaultText(
            modifier = Modifier.fillMaxWidth(),
            text = "YouTube Link"
        )

        DefaultEditText(
            modifier = Modifier.fillMaxWidth(),
            initialValue = state.youtubeUrl,
            enabled = state.status !is Status.InProgress,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.url),
                    contentDescription = "icon"
                )
            },
            trailingIcon = {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.close),
                    contentDescription = "icon"
                )
            },
            onValueChange = onUrlChange
        )

        DefaultText(text = "Destination Folder")

        DestinationSelector(
            modifier = Modifier.fillMaxWidth(),
            isEnabled = state.status !is Status.InProgress,
            selectedDestination = state.storagePath,
            onFileSelectorClicked = onFileSelectorClicked,
            leadingIcon = {
                Icon(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.folder),
                    contentDescription = "icon"
                )
            }
        )

        TextIcon(
            modifier = Modifier,
            iconModifier = Modifier.size(16.dp),
            text = "Where you want to save the MP3",
            icon = R.drawable.info
        )

        if (state.status !is Status.InProgress){
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = state.isGrabbingEnable,
                onClick = onGrabVideoInfo
            )
        } else {
            LoadingButton(
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                onClick = { }
            )
        }

    }
}