package com.task.trendy.data.remote.baseclient.erros

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class ApiError(
    @SerializedName("code") var statusCode: Int,
    @SerializedName("message") var message: String = "",
    @SerializedName("actualCode") var actualCode: String = "-1"
)