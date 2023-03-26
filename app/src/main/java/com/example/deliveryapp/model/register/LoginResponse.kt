package com.example.deliveryapp.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("Data")
    val data: Data,
    @SerializedName("Result")
    val result: Result
)

data class Data(
    @SerializedName("DeliveryName")
    val deliveryName: String
)
data class Result(
    @SerializedName("ErrMsg")
    val errMsg: String,
    @SerializedName("ErrNo")
    val errNo: Int
)