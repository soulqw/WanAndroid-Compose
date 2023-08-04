package com.example.newdemo.core.net.interceptor

import com.example.newdemo.core.net.HttpConstant
import com.test.soultools.tool.log.TLog
import okhttp3.Interceptor
import okhttp3.Response


class SaveCookieInterceptor : Interceptor {

    companion object {
        private const val TAG = "Cookie"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestUrl = request.url.toString()
        val domain = request.url.host
        // set-cookie maybe has multi, login to save cookie
        val cookieString = response.headers(HttpConstant.SET_COOKIE_KEY)
//        if (HttpConstant.checkCookieExpired(cookieString)) {
//            TLog.d(TAG, "cookie 过期，登出")
//            User.clear()
//            return response
//        }
        if ((requestUrl.contains(HttpConstant.SAVE_USER_LOGIN_KEY)
                    || requestUrl.contains(HttpConstant.SAVE_USER_REGISTER_KEY))
            && cookieString.isNotEmpty()
        ) {
            val cookies = response.headers(HttpConstant.SET_COOKIE_KEY)
            val cookie = HttpConstant.encodeCookie(cookies)
            HttpConstant.saveCookie(requestUrl, domain, cookie)
            TLog.d(TAG, requestUrl, domain, cookie)
        }
        TLog.d(TAG, "cookie 正常")
        return response
    }

}