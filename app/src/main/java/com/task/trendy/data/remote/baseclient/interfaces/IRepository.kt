package com.task.trendy.data.remote.baseclient.interfaces


import com.task.trendy.data.remote.baseclient.ApiResponse
import com.task.trendy.data.remote.baseclient.BaseApiResponse
import retrofit2.Response

internal interface IRepository {
    suspend fun <T : BaseApiResponse> executeSafely(call: suspend () -> Response<T>): ApiResponse<T>
}