package com.grpcx.youtubetomp3.data


data class VideoInfo(
    val id: String? = null,
    val fulltitle: String? = null,
    val title: String? = null,

    val uploadDate: String? = null,

    val displayId: String? = null,
    val duration: Int = 0,
    val description: String? = null,
    val thumbnail: String? = null,
    val license: String? = null,
    val extractor: String? = null,

    val extractorKey: String? = null,

    val viewCount: String? = null,

    val likeCount: String? = null,

    val dislikeCount: String? = null,

    val repostCount: String? = null,

    val averageRating: String? = null,

    val uploaderId: String? = null,
    val uploader: String? = null,

    val playerUrl: String? = null,

    val webpageUrl: String? = null,

    val webpageUrlBasename: String? = null,
    val resolution: String? = null,
    val width: Int = 0,
    val height: Int = 0,
    val format: String? = null,

    val formatId: String? = null,
    val ext: String? = null,

    val fileSize: Long = 0,
    val fileSizeApproximate: Long = 0,

    val httpHeaders: Map<String, String>? = null,
    val categories: ArrayList<String>? = null,
    val tags: ArrayList<String>? = null,

    val requestedFormats: List<VideoFormat>? = null,
    val formats: List<VideoFormat>? = null,
    val thumbnails: List<VideoThumbnail>? = null,

    val manifestUrl: String? = null,
    val url: String? = null

)
