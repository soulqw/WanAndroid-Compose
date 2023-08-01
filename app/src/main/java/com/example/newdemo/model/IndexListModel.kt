package com.example.newdemo.model

data class IndexListModel(
    val curPage: Int,
    val datas: List<IndexItem>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)