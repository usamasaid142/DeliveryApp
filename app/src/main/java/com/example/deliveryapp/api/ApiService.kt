package com.example.deliveryapp.api

import com.example.deliveryapp.model.LoginDelivery
import com.example.deliveryapp.model.LoginDeliveryRequest
import com.example.deliveryapp.model.LoginResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("OnyxDeliveryService/Service.svc/CheckDeliveryLogin")
    suspend fun Login(@Body loginDelivery: LoginDeliveryRequest):Response<LoginResponse>
//    @POST("OnyxDeliveryService/Service.svc/CheckDeliveryLogin")
//    suspend fun Login(@Body loginDelivery: LoginDeliveryRequest):Response<ResultResponse<LoginResponse>>



}