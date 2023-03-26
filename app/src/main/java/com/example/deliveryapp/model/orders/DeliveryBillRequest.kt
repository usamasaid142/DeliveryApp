package com.example.deliveryapp.model.orders


import com.google.gson.annotations.SerializedName

data class DeliveryBillRequest(
    @SerializedName("Value")
    val value: DeliveryBillValue?
)
data class DeliveryBillValue(
    @SerializedName("P_DLVRY_NO")
    val pDLVRYNO: String?,
    @SerializedName("P_LANG_NO")
    val pLANGNO: String?
)