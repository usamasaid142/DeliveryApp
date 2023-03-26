package com.example.deliveryapp.viewmodel

import androidx.lifecycle.*
import com.example.deliveryapp.api.ResultEndPoint
import com.example.deliveryapp.api.ResultResponse
import com.example.deliveryapp.model.LoginDelivery
import com.example.deliveryapp.model.LoginDeliveryRequest
import com.example.deliveryapp.model.LoginResponse
import com.example.deliveryapp.model.orders.DeliveryBillsItemsResponse
import com.example.deliveryapp.repository.DeliveryRepository
import com.example.deliveryapp.repository.RemoteDataSource
import com.example.deliveryapp.utils.Resource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val repositry:RemoteDataSource) :
    ViewModel() {

   val loginResponse = MutableLiveData<Resource<LoginResponse>>()
    val deliveryItemsresponse=MutableLiveData<Resource<DeliveryBillsItemsResponse>>()
//    val loginResponse = MutableLiveData<ResultEndPoint<ResultResponse<LoginResponse>>>()

//    fun login(lifecycleOwner: LifecycleOwner,loginDelivery: LoginDeliveryRequest){
//
//        repositry.login(loginDelivery).observe(lifecycleOwner, Observer {
//            loginResponse.value=it
//        })
//
//    }

    fun login(loginDelivery: LoginDeliveryRequest)=viewModelScope.launch(Dispatchers.IO) {
        loginResponse.postValue(Resource.Loading())
        val response=repositry.login(loginDelivery)
        loginResponse.postValue(handleLogin(response))
    }

    private fun handleLogin(response: Response<LoginResponse>): Resource<LoginResponse>? {
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.sucess(it)
            }
        }
        val error = Gson().fromJson<LoginResponse>(response.errorBody()!!.string(), LoginResponse::class.java)
        return Resource.Error(error.result.errMsg)
    }



}