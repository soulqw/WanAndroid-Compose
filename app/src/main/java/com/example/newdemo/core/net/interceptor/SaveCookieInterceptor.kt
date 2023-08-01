package com.example.newdemo.core.net.interceptor

import com.example.newdemo.core.net.HttpConstant
import com.test.soultools.tool.log.TLog
import okhttp3.Interceptor
import okhttp3.Response


class SaveCookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestUrl = request.url.toString()
        val domain = request.url.host
        // set-cookie maybe has multi, login to save cookie
        if ((requestUrl.contains(HttpConstant.SAVE_USER_LOGIN_KEY)
                    || requestUrl.contains(HttpConstant.SAVE_USER_REGISTER_KEY))
            && response.headers(HttpConstant.SET_COOKIE_KEY).isNotEmpty()
        ) {
            val cookies = response.headers(HttpConstant.SET_COOKIE_KEY)
            val cookie = HttpConstant.encodeCookie(cookies)
            HttpConstant.saveCookie(requestUrl, domain, cookie)
            TLog.d("qw", requestUrl, domain, cookie)
        }
        return response
    }

}