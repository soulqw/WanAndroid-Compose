package com.example.newdemo.core.net.interceptor

import com.example.newdemo.core.net.HttpConstant
import com.example.newdemo.core.uitils.SpCenter
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
        val builder = request.newBuilder()

        builder.addHeader("Content-type", "application/json; charset=utf-8")
        val domain = request.url.host
        if (domain.isNotEmpty()
        ) {
            val cookie: String = SpCenter.COOKIE
            if (cookie.isNotEmpty()) {
                // 将 Cookie 添加到请求头
                builder.addHeader(HttpConstant.COOKIE_NAME, cookie)
            }
        }
        return chain.proceed(builder.build())
    }

}