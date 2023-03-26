package com.example.deliveryapp.model


import com.google.gson.annotations.SerializedName

data class LoginDeliveryRequest(
    @SerializedName("Value")
    val value: LoginDelivery
)

data class LoginDelivery(
    @SerializedName("P_DLVRY_NO")
    val pDLVRYNO: String,
    @SerializedName("P_LANG_NO")
    val pLANGNO: String,
    @SerializedName("P_PSSWRD")
    val pPSSWRD: String
)