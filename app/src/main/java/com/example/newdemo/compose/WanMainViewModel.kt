package com.example.newdemo.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newdemo.model.IndexItem
import com.example.newdemo.repo.MainServiceImp
import com.test.soultools.tool.log.TLog
import kotlinx.coroutines.launch


class WanMainViewModel : ViewModel() {

    companion object {

        private const val TAG = "WanMainViewModel"

    }

    /**
     * onclick start
     */
    private val _itemClickData = MutableLiveData<IndexItem>()

    val onClickData: LiveData<IndexItem> = _itemClickData

    fun openArticleDetail(article: IndexItem) {
        TLog.d(TAG, article)
        _itemClickData.value = article
    }

    /**
     * onIndex click end
     */

    //index list start

    private val _itemIndexData = mutableStateListOf<IndexItem>()

//    private val _itemIndexData by mutableStateListOf<IndexItem>()

    val itemIndexData: List<IndexItem> = _itemIndexData

    var loadMoreIndex = 0

    private var isRequesting = false

    fun refreshIndexArticle() {
        loadMoreIndex = 0
        _itemIndexData.clear()
        viewModelScope.launch {
            val data = MainServiceImp.getIndexArticles(loadMoreIndex)
            TLog.d(TAG, data.toString())
            val dataList = data.data.datas
            _itemIndexData.addAll(dataList)
        }
    }

    fun loadMoreIndexArticle() {
        TLog.d(TAG, isRequesting)
        if (isRequesting) {
            return
        }
        isRequesting = true
        loadMoreIndex++
        viewModelScope.launch {
            val data = MainServiceImp.getIndexArticles(loadMoreIndex)
            TLog.d(TAG, data.toString())
            val dataList = data.data.datas
            _itemIndexData.addAll(dataList)
            isRequesting = false
        }

    }

    //index list end


}