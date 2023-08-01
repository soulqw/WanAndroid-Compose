package com.example.newdemo.repo

import com.example.newdemo.core.net.NetWorkCore
import com.example.newdemo.model.BusinessResp
import com.example.newdemo.model.IndexListModel
import retrofit2.Response

object MainServiceImp {

    private val api by lazy {
        NetWorkCore.retrofit.create(MainServices::class.java)
    }

    suspend fun getIndexArticles(index: Int): Response<BusinessResp<IndexListModel>> {
        return api.getIndexArticles(index.toString())
    }

    suspend fun loginWanAndroid(
        username: String,
        password: String
    ): Response<BusinessResp<LoginData>> {
        return api.loginWanAndroid(username, password)
    }

}