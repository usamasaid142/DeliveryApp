package com.example.deliveryapp.model.orders


import com.google.gson.annotations.SerializedName

data class DeliveryBillsItemsResponse(
    @SerializedName("Data")
    val data: Data,
    @SerializedName("Result")
    val result: Result
)