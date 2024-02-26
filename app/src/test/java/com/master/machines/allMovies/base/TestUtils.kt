package com.master.machines.allMovies.base

import com.master.machines.allMovies.data.models.ResponseAllMoviesDTO
import okhttp3.ResponseBody.Companion.toResponseBody
import retrofit2.Response

object TestUtils {

    val responseResponseAllMoviesDTO
        get() = ResponseAllMoviesDTO(
            results = listOf()
        )
    val <T> T.responseSuccess: Response<T> get() = Response.success(this)
    fun <T> T.responseNotSuccess(code: Int, message: String): Response<T> =
        Response.error(code, message.toResponseBody(null))
}