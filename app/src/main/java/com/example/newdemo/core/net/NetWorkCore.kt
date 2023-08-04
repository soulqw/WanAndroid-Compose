package com.example.newdemo.core.net

import com.example.newdemo.BuildConfig
import com.example.newdemo.core.net.interceptor.HeaderInterceptor
import com.example.newdemo.core.net.interceptor.SaveCookieInterceptor
import com.test.soultools.tool.log.TLog
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object NetWorkCore {

    private const val TAG = "qw"

    val retrofit: Retrofit by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(HttpConstant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(getOkHttpClient())
            .build()
        retrofit
    }

    private fun getOkHttpClient(): OkHttpClient {

        val builder = OkHttpClient().newBuilder().apply {
            addInterceptor(configLogger())
            addInterceptor(SaveCookieInterceptor())
            addInterceptor(HeaderInterceptor())
        }
        return builder.build()
    }

    private fun configLogger(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor.Logger { message ->
//            if (message.startsWith("{") || message.startsWith("[")) {
//                TLog.printJson(TAG, message, "---------------------")
//            } else {
                TLog.d(TAG, message)
//            }
        }
        return HttpLoggingInterceptor(logger).also {
            if (BuildConfig.DEBUG) {
                it.level = HttpLoggingInterceptor.Level.BODY
            } else {
                it.level = HttpLoggingInterceptor.Level.NONE
            }
        }
    }
}