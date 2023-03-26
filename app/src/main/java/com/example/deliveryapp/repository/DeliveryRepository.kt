package com.example.deliveryapp.repository

import com.example.deliveryapp.api.resultNetworkLiveData
import com.example.deliveryapp.model.LoginDelivery
import com.example.deliveryapp.model.LoginDeliveryRequest
import com.example.deliveryapp.model.LoginResponse
import javax.inject.Inject

class DeliveryRepository @Inject constructor(private val remoteSource:RemoteDataSource) {

//    fun login(login: LoginDeliveryRequest) =
//        resultNetworkLiveData(networkCall = {
//            remoteSource.login(
//                login
//            )
//        })
}