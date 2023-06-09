package com.example.deliveryapp.utils

import android.content.Context
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    lateinit var context: Context
    fun setup(context: Context) {
        this.context = context
    }

    fun convertLongToDate(time:Long):String{
        val date= Date(time)
        val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.US)
        return sdf.format(date)
    }

    fun Long.toTimeDateString(): String {
        val dateTime = Date(this)
        val format = SimpleDateFormat(" yyyy/MM/dd", Locale.US)
        return format.format(dateTime)
    }
    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("yyyy.MM.dd")
        return df.parse(date).time
    }



}