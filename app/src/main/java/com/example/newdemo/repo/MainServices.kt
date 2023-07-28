package com.example.newdemo.repo

import com.example.newdemo.model.IndexModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MainServices {

    @GET("article/list/{index}/json")
    suspend fun getIndexArticles(@Path("index") index: String): IndexModel

}