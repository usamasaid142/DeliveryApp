package com.example.deliveryapp.repository

import com.example.deliveryapp.api.ApiService
import com.example.deliveryapp.api.BaseDataSource
import com.example.deliveryapp.model.LoginDelivery
import com.example.deliveryapp.model.LoginDeliveryRequest
import com.example.deliveryapp.model.LoginResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService):BaseDataSource(){

//    suspend fun login(login: LoginDeliveryRequest) =
//        getResult { apiService.Login(login) }

    suspend fun login(loginUser: LoginDeliveryRequest)=apiService.Login(loginUser)
}