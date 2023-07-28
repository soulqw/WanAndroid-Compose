package com.example.newdemo.core.net

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetWorkCore {

    val retrofit: Retrofit by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit
    }

}