package com.grpcx.youtubetomp3

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.grpcx.youtubetomp3.data.AppState
import com.grpcx.youtubetomp3.domain.MainActivityViewModel
import com.grpcx.youtubetomp3.ui.composables.TextIcon
import com.grpcx.youtubetomp3.ui.screen.download.DownloadScreen
import com.grpcx.youtubetomp3.ui.screen.grab.GrabScreen
import com.grpcx.youtubetomp3.ui.screen.initial.InitialScreen
import com.grpcx.youtubetomp3.ui.theme.YoutubeToMp3Theme
import com.yausername.youtubedl_android.YoutubeDL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var youtubeDlp : YoutubeDL

    private val viewModel: MainActivityViewModel by viewModels()

    private val selectFileDirectory = registerForActivityResult(
        ActivityResultContracts
            .OpenDocumentTree()
    ) { directory ->
        viewModel.updateDestination(directory?.path.orEmpty())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        youtubeDlp.init(this)
        setContent {
            YoutubeToMp3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenController(
                        viewModel = viewModel,
                        onFileSelectorClicked = {
                            selectFileDestination()
                        }
                    )
                }
            }
        }
    }

    private fun selectFileDestination() {
        selectFileDirectory.launch(null)
    }
}

@Composable
fun ScreenController(
    viewModel: MainActivityViewModel,
    onFileSelectorClicked: () -> Unit = {},
) {

    val state = viewModel.mainAppState.collectAsState(initial = AppState.InitialState()).value

    Column(
        modifier = Modifier
            .padding(
                horizontal = 20.dp,
                vertical = 40.dp
            ),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        TextIcon(
            iconModifier = Modifier.size(16.dp),
            text = "MP3 Downloader",
            icon = R.drawable.youtube
        )

        ScreenContent(
            appState = state,
            viewModel = viewModel,
            onFileSelectorClicked = onFileSelectorClicked,
        )

    }
}

@Composable
fun ScreenContent(
    appState: AppState,
    viewModel: MainActivityViewModel,
    onFileSelectorClicked: () -> Unit,
) {
    when (appState) {
        is AppState.InitialState -> InitialScreen(
            state = appState,
            onUrlChange = viewModel::updateYoutubeUrl,
            onFileSelectorClicked = onFileSelectorClicked,
            onGrabVideoInfo = viewModel::retrieveVideo
        )
        is AppState.DownloadingState -> DownloadScreen(
            appState = appState
        )
        is AppState.ConvertingState -> {}
        is AppState.SavingStatus -> {}
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen() {
    ScreenController(viewModel = viewModel())
}