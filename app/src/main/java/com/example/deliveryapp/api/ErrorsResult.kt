package com.example.deliveryapp.api

import com.google.gson.annotations.SerializedName

data class ErrorsResult(
    @SerializedName("message")  val message: List<String>?,
    @SerializedName("password")  val password: List<String>?,
    @SerializedName("audio")  val audio: List<String>?,
)