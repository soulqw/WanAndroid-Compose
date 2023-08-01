package com.example.newdemo.repo

import com.example.newdemo.model.BusinessResp
import com.example.newdemo.model.IndexListModel
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainServices {

    @GET("article/list/{index}/json")
    suspend fun getIndexArticles(@Path("index") index: String): Response<BusinessResp<IndexListModel>>

    @POST("user/login")
    @FormUrlEncoded
    suspend fun loginWanAndroid(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<BusinessResp<LoginData>>

}