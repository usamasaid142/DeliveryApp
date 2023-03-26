package com.example.deliveryapp.model.orders


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("DeliveryBills")
    val deliveryBills: List<DeliveryBill>
)