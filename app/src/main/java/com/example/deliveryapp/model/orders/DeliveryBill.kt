package com.example.deliveryapp.model.orders


import com.google.gson.annotations.SerializedName

data class DeliveryBill(
    @SerializedName("BILL_AMT")
    val bILLAMT: String,
    @SerializedName("BILL_DATE")
    val bILLDATE: String,
    @SerializedName("BILL_NO")
    val bILLNO: String,
    @SerializedName("BILL_SRL")
    val bILLSRL: String,
    @SerializedName("BILL_TIME")
    val bILLTIME: String,
    @SerializedName("BILL_TYPE")
    val bILLTYPE: String,
    @SerializedName("CSTMR_ADDRSS")
    val cSTMRADDRSS: String,
    @SerializedName("CSTMR_APRTMNT_NO")
    val cSTMRAPRTMNTNO: String,
    @SerializedName("CSTMR_BUILD_NO")
    val cSTMRBUILDNO: String,
    @SerializedName("CSTMR_FLOOR_NO")
    val cSTMRFLOORNO: String,
    @SerializedName("CSTMR_NM")
    val cSTMRNM: String,
    @SerializedName("DLVRY_AMT")
    val dLVRYAMT: String,
    @SerializedName("DLVRY_STATUS_FLG")
    val dLVRYSTATUSFLG: String,
    @SerializedName("LATITUDE")
    val lATITUDE: String,
    @SerializedName("LONGITUDE")
    val lONGITUDE: String,
    @SerializedName("MOBILE_NO")
    val mOBILENO: String,
    @SerializedName("RGN_NM")
    val rGNNM: String,
    @SerializedName("TAX_AMT")
    val tAXAMT: String
)