package com.example.newdemo.model

data class BusinessResp<T>(
    val `data`: T,
    val errorCode: Int,
    val errorMsg: String
)