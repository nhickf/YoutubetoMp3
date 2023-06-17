package com.grpcx.youtubetomp3.repository

import com.grpcx.youtubetomp3.data.VideoInfo
import javax.inject.Inject


interface FakeRepository {

    var videoInfo : VideoInfo?

}

class FakeRepositoryImpl @Inject constructor() : FakeRepository {
    override var videoInfo: VideoInfo? = null
}