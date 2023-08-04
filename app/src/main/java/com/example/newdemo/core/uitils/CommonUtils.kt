package com.example.newdemo.core.uitils

import android.widget.Toast
import com.example.newdemo.AppApplication

object CommonUtils {

    fun shortToast(message: String) {
        Toast.makeText(AppApplication.getInstance(), message, Toast.LENGTH_SHORT).show()
    }

    fun longToast(message: String) {
        Toast.makeText(AppApplication.getInstance(), message, Toast.LENGTH_LONG).show()
    }
}