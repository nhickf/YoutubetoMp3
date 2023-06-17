package com.grpcx.youtubetomp3.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.yausername.youtubedl_android.mapper.VideoFormat
import com.grpcx.youtubetomp3.data.VideoFormat as FormatVideo
import com.yausername.youtubedl_android.mapper.VideoInfo
import com.yausername.youtubedl_android.mapper.VideoThumbnail
import com.grpcx.youtubetomp3.data.VideoThumbnail as VideoTN
import com.grpcx.youtubetomp3.data.VideoInfo as Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

inline fun <T> Flow<T>.safeCollect(
    lifecycleOwner: LifecycleOwner,
    noinline action: suspend (T) -> Unit
) = lifecycleOwner.lifecycleScope.launch {
    flowWithLifecycle(lifecycleOwner.lifecycle)
        .onEach(action)
        .collect()
}

fun VideoInfo.mapToDataClass(): Video {
    return Video(
        id,
        fulltitle,
        title,
        uploadDate,
        displayId,
        duration,
        description,
        thumbnail,
        license,
        extractor,
        extractorKey,
        viewCount,
        likeCount,
        dislikeCount,
        repostCount,
        averageRating,
        uploaderId,
        uploader,
        playerUrl,
        webpageUrl,
        webpageUrlBasename,
        resolution,
        width,
        height,
        format,
        formatId,
        ext,
        fileSize,
        fileSizeApproximate,
        httpHeaders,
        categories,
        tags,
        requestedFormats?.map { it.mapToDataClass() }.orEmpty(),
        formats?.map { it.mapToDataClass() }.orEmpty(),
        thumbnails?.map { it.mapToDataClass() },
        manifestUrl, url
    )
}

fun VideoFormat.mapToDataClass(): FormatVideo {
    return FormatVideo(
        asr,
        tbr,
        0,
        format,
        formatId,
        formatNote,
        ext,
        preference,
        vcodec,
        acodec,
        width,
        height,
        fileSize,
        fileSizeApproximate,
        fps,
        url,
        manifestUrl,
        httpHeaders
    )
}

fun VideoThumbnail.mapToDataClass(): VideoTN {
    return VideoTN(
        url, id
    )
}