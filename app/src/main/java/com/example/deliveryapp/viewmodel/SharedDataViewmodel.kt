package com.example.deliveryapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.deliveryapp.model.OrderBYDate

class SharedDataViewmodel:ViewModel() {

    val date=MutableLiveData<OrderBYDate>()
    val lang=MutableLiveData<String>()


    fun getDate(orderBYDate:OrderBYDate){
        date.postValue(orderBYDate)
    }
    fun getLanguageNo(langNo:String){
        lang.postValue(langNo)
    }

}