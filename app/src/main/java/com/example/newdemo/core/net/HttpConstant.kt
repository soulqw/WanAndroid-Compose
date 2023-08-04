package com.example.newdemo.core.net

import android.annotation.SuppressLint
import com.example.newdemo.core.uitils.SpCenter
import com.test.soultools.tool.log.TLog
import java.text.SimpleDateFormat
import java.util.Date


object HttpConstant {

    const val BASE_URL = "https://www.wanandroid.com"

    const val DEFAULT_TIMEOUT: Long = 15

    const val SAVE_USER_LOGIN_KEY = "user/login"

    const val SAVE_USER_REGISTER_KEY = "user/register"

    const val COLLECTIONS_WEBSITE = "lg/collect"

    const val UNCOLLECTIONS_WEBSITE = "lg/uncollect"

    const val ARTICLE_WEBSITE = "article"

    const val TODO_WEBSITE = "lg/todo"

    const val COIN_WEBSITE = "lg/coin"

    const val SET_COOKIE_KEY = "set-cookie"
    const val COOKIE_NAME = "Cookie"

    const val TAG = "HttpConstant"

    const val MAX_CACHE_SIZE: Long = 1024 * 1024 * 50 // 50M 的缓存大小

    fun encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies
            .map { cookie ->
                cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            }
            .forEach {
                it.filterNot { set.contains(it) }.forEach { set.add(it) }
            }
        val ite = set.iterator()
        while (ite.hasNext()) {
            val cookie = ite.next()
            sb.append(cookie).append(";")
        }
        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }

    fun saveCookie(url: String?, domain: String?, cookies: String) {
        url ?: return
//        var spUrl: String by Preference(url, cookies)
//        @Suppress("UNUSED_VALUE")
//        spUrl = cookies
//        domain ?: return
//        var spDomain: String by Preference(domain, cookies)
//        @Suppress("UNUSED_VALUE")
//        spDomain = cookies
        SpCenter.COOKIE = cookies
    }

    @SuppressLint("SimpleDateFormat")
    fun checkCookieExpired(cookies: List<String>?): Boolean {
        if (cookies.isNullOrEmpty() || cookies.size < 2) {
            return true
        }
        try {
            val time = cookies[1]
                .split(";")[1]
                .replace(" Expires=", "")
            val dateFormat = SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss z")
            val targetDate = dateFormat.parse(time)
            val currentDate = Date()
            return if (currentDate.before(targetDate)) {
                TLog.d(TAG, "没过期")
                false
            } else {
                TLog.d(TAG, "过期")
                true
            }
        } catch (e: Exception) {
            TLog.d(TAG, e)
        }
        return true
    }

}