package com.example.deliveryapp.api

import com.example.deliveryapp.model.LoginDelivery
import com.example.deliveryapp.model.LoginDeliveryRequest
import com.example.deliveryapp.model.LoginResponse
import com.example.deliveryapp.model.orders.DeliveryBillRequest
import com.example.deliveryapp.model.orders.DeliveryBillsItemsResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("OnyxDeliveryService/Service.svc/CheckDeliveryLogin")
    suspend fun Login(@Body loginDelivery: LoginDeliveryRequest):Response<LoginResponse>
//    @POST("OnyxDeliveryService/Service.svc/CheckDeliveryLogin")
//    suspend fun Login(@Body loginDelivery: LoginDeliveryRequest):Response<ResultResponse<LoginResponse>>

    @POST("OnyxDeliveryService/Service.svc/GetDeliveryBillsItems")
    suspend fun getDeliveryBillsItems(@Body deliveryBillRequest: DeliveryBillRequest):Response<DeliveryBillsItemsResponse>

}