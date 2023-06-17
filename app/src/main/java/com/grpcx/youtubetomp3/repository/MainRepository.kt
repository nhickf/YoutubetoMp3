package com.grpcx.youtubetomp3.repository

import com.grpcx.youtubetomp3.data.BaseResponse
import com.grpcx.youtubetomp3.data.Response
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun getYoutubeVideoInfo (youtubeUrl : String) : Flow<Response>

}