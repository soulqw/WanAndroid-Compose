package com.example.newdemo.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.newdemo.core.uitils.SpCenter
import com.google.gson.Gson
import java.lang.Exception

data class User(
    val admin: Boolean = false,
    val chapterTops: List<Any> = emptyList(),
    val coinCount: Int = 0,
    val collectIds: List<Int> = emptyList(),
    val email: String = "",
    val icon: String = "",
    val id: Int = 0,
    val nickname: String = "",
    val password: String = "",
    val publicName: String = "",
    val token: String = "",
    val type: Int = 0,
    val username: String = ""
) {
    fun isValid(): Boolean {
        return username.isNotBlank() && id != -1
    }

    fun getShowName(): String {
//        if (nickname.isNotBlank()) {
//            return nickname
//        }
        return username
    }

    companion object {

        var current by mutableStateOf(empty())

        private fun empty(): User {
            return User(username = "", id = -1)
        }

        fun alreadyLogin(): Boolean {
            val current = get()
            if (current != null && current.isValid()) {
                return true
            }
            return false
        }

        fun get(): User? {
            val cur = current
            if (cur.isValid()) {
                return cur
            }
            val spUser = SpCenter.USER
            if (spUser.isBlank()) {
                return null
            }
            return try {
                current = Gson().fromJson(spUser, User::class.java)
                current
            } catch (e: Exception) {
                null
            }
        }

        fun update(user: User) {
            current = user
            val userString = Gson().toJson(user)
            SpCenter.USER = userString
        }

        fun clear() {
            val empty = empty()
            current = empty
            val userString = Gson().toJson(empty)
            SpCenter.USER = userString
        }
    }
}