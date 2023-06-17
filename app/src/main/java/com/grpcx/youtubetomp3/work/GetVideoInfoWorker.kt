package com.grpcx.youtubetomp3.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.grpcx.youtubetomp3.repository.FakeRepository
import com.grpcx.youtubetomp3.utils.mapToDataClass
import com.yausername.youtubedl_android.YoutubeDL
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class GetVideoInfoWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted params: WorkerParameters,
    private val youtubeDlp: YoutubeDL,
    private val fakeRepository: FakeRepository
) : CoroutineWorker(
    context,
    params
) {
    override suspend fun doWork(): Result {
        val url = inputData.getString(VIDEO_URL_KEY)
        if (url.isNullOrBlank()) return Result.failure()
        return try {
            fakeRepository.videoInfo = youtubeDlp.getInfo(url).mapToDataClass()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }

    companion object {
        const val VIDEO_URL_KEY = "video_url"
        const val VIDEO_KEY = "video"
        const val WORKER_TAG = "get_video"
    }
}