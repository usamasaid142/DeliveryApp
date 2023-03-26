package com.example.deliveryapp.utils

import android.content.Context
import android.widget.Toast



fun Context.displayToastText(txt: String){
    Toast.makeText(this,txt, Toast.LENGTH_SHORT).show()
}

fun Context.stringText(res: Int): String{
    return this.resources.getString(res)
}




