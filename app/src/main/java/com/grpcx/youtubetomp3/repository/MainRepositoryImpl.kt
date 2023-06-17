package com.grpcx.youtubetomp3.repository

import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.await
import androidx.work.workDataOf
import com.grpcx.youtubetomp3.data.Response
import com.grpcx.youtubetomp3.utils.mapToDataClass
import com.grpcx.youtubetomp3.work.GetVideoInfoWorker
import com.yausername.youtubedl_android.YoutubeDL
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class MainRepositoryImpl @Inject constructor(
    private val youtubeDlp: YoutubeDL,
    private val workManager: WorkManager,
) : MainRepository {

    override suspend fun getYoutubeVideoInfo(youtubeUrl: String): Flow<Response> {
        return flow {
            emit(Response.Loading)
            delay(1000L)
            try {
                emit(Response.Success(youtubeDlp.getInfo(youtubeUrl).mapToDataClass()))
            } catch (e: Exception) {
                emit(Response.Failed(e.message.toString()))
            }
        }
    }

    private suspend fun getVideo () {

    }
}