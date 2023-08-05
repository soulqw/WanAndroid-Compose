package com.example.newdemo.repo

import com.example.newdemo.model.BusinessResp
import com.example.newdemo.model.IndexItem
import com.example.newdemo.model.IndexListModel
import com.example.newdemo.model.UserProfile
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MainServices {

    @GET("article/top/json")
    suspend fun getTopArticles(): Response<BusinessResp<List<IndexItem>>>

    @GET("article/list/{index}/json")
    suspend fun getIndexArticles(@Path("index") index: Int): Response<BusinessResp<IndexListModel>>

    @POST("user/login")
    @FormUrlEncoded
    suspend fun loginWanAndroid(
        @Field("username") username: String,
        @Field("password") password: String
    ): Response<BusinessResp<LoginData>>

    /**
     * 即cookie max-Age=0
     */
    @GET("user/logout/json")
    suspend fun logoutWanAndroid(): Response<BusinessResp<String>>

    @GET("/user/lg/userinfo/json")
    suspend fun getUserProfileInfo(): Response<BusinessResp<UserProfile>>


}