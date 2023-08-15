package com.example.newdemo.repo

import com.example.newdemo.core.net.NetWorkCore
import com.example.newdemo.model.BannerModel
import com.example.newdemo.model.BusinessResp
import com.example.newdemo.model.IndexItem
import com.example.newdemo.model.IndexListModel
import com.example.newdemo.model.UserProfile
import retrofit2.Response

object MainServiceImp {

    private val api by lazy {
        NetWorkCore.retrofit.create(MainServices::class.java)
    }

    suspend fun getTopArticles(): Response<BusinessResp<List<IndexItem>>> {
        return api.getTopArticles()
    }

    suspend fun getIndexArticles(index: Int): Response<BusinessResp<IndexListModel>> {
        return api.getIndexArticles(index)
    }

    suspend fun getBanners(): Response<BusinessResp<List<BannerModel>>> {
        return api.getBanners()
    }

    suspend fun loginWanAndroid(
        username: String,
        password: String
    ): Response<BusinessResp<LoginData>> {
        return api.loginWanAndroid(username, password)
    }

    suspend fun registerWanAndroid(
        username: String,
        password: String,
        rePassword: String,
    ): Response<BusinessResp<LoginData>> {
        return api.registerWanAndroid(username, password, rePassword)
    }

    suspend fun logoutWanAndroid(): Response<BusinessResp<String>> {
        return api.logoutWanAndroid()
    }

    suspend fun getUserProfileInfo(): Response<BusinessResp<UserProfile>> {
        return api.getUserProfileInfo()
    }

}