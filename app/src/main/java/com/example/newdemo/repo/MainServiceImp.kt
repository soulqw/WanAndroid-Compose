package com.example.newdemo.repo

import com.example.newdemo.core.net.NetWorkCore
import com.example.newdemo.model.IndexModel

object MainServiceImp {

    private val api by lazy {
        NetWorkCore.retrofit.create(MainServices::class.java)
    }

    suspend fun getIndexArticles(index: Int): IndexModel {
        return api.getIndexArticles(index.toString())
    }

}