package com.example.newdemo

import android.app.Application
import com.example.newdemo.core.uitils.SpCenter

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SpCenter.init(this)
    }
}