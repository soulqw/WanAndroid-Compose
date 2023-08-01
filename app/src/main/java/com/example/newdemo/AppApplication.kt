package com.example.newdemo

import android.app.Application
import com.example.newdemo.core.uitils.SpCenter

class AppApplication : Application() {

    companion object {
        private lateinit var app: AppApplication
        fun getInstance(): AppApplication {
            return app
        }
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        SpCenter.init(this)
    }
}