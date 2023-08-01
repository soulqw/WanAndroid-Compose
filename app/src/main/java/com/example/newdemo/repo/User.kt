package com.example.newdemo.repo

import androidx.compose.runtime.mutableStateOf
import com.example.newdemo.core.uitils.SpCenter
import com.google.gson.Gson
import java.lang.Exception

class User(val username: String, val id: Int) {

    fun isValid(): Boolean {
        return username.isNotBlank() && id != -1;
    }

    companion object {

        var current = mutableStateOf(empty())

        private fun empty(): User {
            return User("", -1)
        }

        fun isLogin(): Boolean {
            return get() != null
        }

        fun get(): User? {
            val cur = current.value
            if (cur.isValid()) {
                return cur
            }
            val spUser = SpCenter.USER
            if (spUser.isBlank()) {
                return null
            }
            return try {
                current.value = Gson().fromJson(spUser, User::class.java)
                current.value
            } catch (e: Exception) {
                null
            }
        }

        fun update(user: User) {
            current.value = user
            val userString = Gson().toJson(user)
            SpCenter.USER = userString
        }
    }

}