package com.grpcx.youtubetomp3.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grpcx.youtubetomp3.data.AppState
import com.grpcx.youtubetomp3.data.Response
import com.grpcx.youtubetomp3.data.Status
import com.grpcx.youtubetomp3.data.VideoInfo
import com.grpcx.youtubetomp3.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _mainAppState: MutableStateFlow<AppState> =
        MutableStateFlow(AppState.InitialState())
    val mainAppState = _mainAppState.shareIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000L),
        replay = 2
    )

    fun retrieveVideo() {
        viewModelScope.launch(Dispatchers.IO) {
            mainRepository.getYoutubeVideoInfo(_mainAppState.value.youtubeUrl)
                .onEach {
                    if (_mainAppState.value is AppState.InitialState) {
                        val appState = _mainAppState.value as AppState.InitialState
                        when (it) {
                            Response.Loading -> {
                                _mainAppState.update {
                                    appState.copy(
                                        status = Status.InProgress(),
                                    )
                                }
                            }

                            is Response.Success<*> -> {
                                _mainAppState.emit(
                                    AppState.DownloadingState(
                                        storagePath = _mainAppState.value.storagePath,
                                        videoInfo = it.data as VideoInfo
                                    )
                                )
                            }

                            is Response.Failed -> {
                                _mainAppState.update { _ ->
                                    appState.copy(
                                        status = Status.Failed(
                                            message = it.message
                                        ),
                                    )
                                }
                            }
                            else -> {}
                        }
                    }
                }.collect()
        }
    }

    fun updateDestination(path: String) {
        viewModelScope.launch {
            _mainAppState.update {
                if (it is AppState.InitialState) {
                    it.copy(
                        storagePath = path,
                    )
                } else return@launch
            }
        }
    }

    fun updateYoutubeUrl(url: String) {
        viewModelScope.launch {
            _mainAppState.update {
                if (it is AppState.InitialState) {
                    it.copy(
                        youtubeUrl = url,
                    )
                } else return@launch
            }
        }
    }
}