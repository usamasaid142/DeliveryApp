package com.example.deliveryapp.model.orders


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("ErrMsg")
    val errMsg: String,
    @SerializedName("ErrNo")
    val errNo: Int
)