package com.grpcx.youtubetomp3.data

data class VideoFormat(
    val asr: Int = 0,
    val tbr: Int = 0,
    val ar: Int = 0,
    val format: String? = null,

    val formatId: String? = null,

    val formatNote: String? = null,
    val ext: String? = null,
    val preference: Int = 0,
    val vcodec: String? = null,
    val acodec: String? = null,
    val width: Int = 0,
    val height: Int = 0,

    val fileSize: Long = 0,

    val fileSizeApproximate: Long = 0,
    val fps: Int = 0,
    val url: String? = null,

    val manifestUrl: String? = null,

    val httpHeaders: Map<String, String>? = null

)
