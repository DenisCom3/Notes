package com.denis.notes.utilits

import android.annotation.SuppressLint
import android.widget.Toast

@SuppressLint("ShowToast")
fun showToast(message:String){
    Toast.makeText(APP_ACTIVITY,message, Toast.LENGTH_SHORT).show()
}