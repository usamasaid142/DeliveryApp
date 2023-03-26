package com.example.deliveryapp.api

import com.google.gson.annotations.SerializedName

data class ResultResponse<T>(
    @SerializedName("status") val status: Boolean,
    @SerializedName("data") val data: T,
    @SerializedName("error") val error: ErrorsResult?,
    @SerializedName("code") val code: Int,
    @SerializedName("token") val token: String?
)