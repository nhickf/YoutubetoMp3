package com.grpcx.youtubetomp3.ui.screen.download

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.grpcx.youtubetomp3.data.AppState
import com.grpcx.youtubetomp3.data.Thumbnail
import com.grpcx.youtubetomp3.ui.composables.DefaultCard

@Preview
@Composable
fun DefaultScreen() {

}

@Composable
fun DownloadScreen(
    appState: AppState.DownloadingState
) {

    val thumbNail = Thumbnail(
        imageUrl = appState.videoInfo.thumbnail.orEmpty(),
        videoTitle = appState.videoInfo.fulltitle.orEmpty(),
        videoLikes = appState.videoInfo.likeCount.orEmpty(),
        videoViews = appState.videoInfo.viewCount.orEmpty()
    )

    DefaultCard(thumbnail = thumbNail)
}

