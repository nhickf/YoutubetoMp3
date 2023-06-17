package com.grpcx.youtubetomp3.data

data class BaseResponse(
    val response : Response = Response.Nothing
)

sealed class Response {

    object Nothing : Response()

    object Loading : Response()

    data class Success<T>(val data : T) : Response()

    data class Failed(val message : String) : Response()

}