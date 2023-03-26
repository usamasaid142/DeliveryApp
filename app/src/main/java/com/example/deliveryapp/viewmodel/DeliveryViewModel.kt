package com.example.deliveryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.model.LoginDeliveryRequest
import com.example.deliveryapp.model.LoginResponse
import com.example.deliveryapp.model.orders.DeliveryBillRequest
import com.example.deliveryapp.model.orders.DeliveryBillsItemsResponse
import com.example.deliveryapp.repository.RemoteDataSource
import com.example.deliveryapp.utils.Resource
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DeliveryViewModel @Inject constructor(private val repositry: RemoteDataSource) : ViewModel() {

    val deliveryItemsresponse= MutableLiveData<Resource<DeliveryBillsItemsResponse>>()

    fun getDeliveryBillsItems(deliveryBillRequest: DeliveryBillRequest)=viewModelScope.launch(Dispatchers.IO) {
        deliveryItemsresponse.postValue(Resource.Loading())
        val response=repositry.getDeliveryBillsItems(deliveryBillRequest)
        deliveryItemsresponse.postValue(handleGetDeliveryBillsItems(response))
    }

    private fun handleGetDeliveryBillsItems(response: Response<DeliveryBillsItemsResponse>): Resource<DeliveryBillsItemsResponse>? {
        if (response.isSuccessful){
            response.body()?.let {
                return Resource.sucess(it)
            }
        }
        val error = Gson().fromJson<DeliveryBillsItemsResponse>(response.errorBody()!!.string(), DeliveryBillsItemsResponse::class.java)
        return Resource.Error(error.result.errMsg)
    }
}