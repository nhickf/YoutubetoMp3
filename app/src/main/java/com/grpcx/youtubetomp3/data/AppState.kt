package com.grpcx.youtubetomp3.data


sealed class AppState (
    open val storagePath: String = "",
    open val youtubeUrl: String = "",
    open val status : Status = Status.Nothing,
) {

    data class InitialState(
        override val storagePath: String = "",
        override val youtubeUrl: String = "",
        override val status : Status = Status.Nothing,
    ) : AppState() {
        val isGrabbingEnable: Boolean = storagePath.isNotBlank() && youtubeUrl.isNotBlank()
    }

    data class DownloadingState(
        override val storagePath: String,
        val videoInfo: VideoInfo
    ) : AppState()

    data class ConvertingState(
        val videoData: String,
    ) : AppState()

    data class SavingStatus(
        val videoData: String,
    ) : AppState()
}

sealed class Status {

    object Nothing : Status()

    data class InProgress(
        val progress: String? = null
    ) : Status()

    data class Done<T>(val data : T) : Status()

    data class Failed(
        val message: String? = null
    ) : Status()
}