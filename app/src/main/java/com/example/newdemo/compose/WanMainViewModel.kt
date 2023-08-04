package com.example.newdemo.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newdemo.core.uitils.requestData
import com.example.newdemo.model.IndexItem
import com.example.newdemo.model.User
import com.example.newdemo.repo.MainServiceImp
import com.test.soultools.tool.log.TLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class WanMainViewModel : ViewModel() {


    companion object {

        private const val TAG = "WanMainViewModel"

    }

    //index list start

    private val _itemIndexData = mutableStateListOf<IndexItem>()

//    private val _itemIndexData by mutableStateListOf<IndexItem>()

    val itemIndexData: List<IndexItem> = _itemIndexData

    var loadMoreIndex = 0

    private var isRequesting = false

    private var refreshJob: Job? = null

    fun refreshIndexArticle() {
        TLog.d(TAG)
        refreshJob?.cancel()
        loadMoreIndex = 0
        _itemIndexData.clear()
        refreshJob = viewModelScope.launch {
            val result = requestData {
                MainServiceImp.getIndexArticles(loadMoreIndex)
            } ?: return@launch
            TLog.d(TAG, result)
            val dataList = result.datas
            _itemIndexData.addAll(dataList)
            refreshJob = null
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
            val result = requestData {
                MainServiceImp.getIndexArticles(loadMoreIndex)
            } ?: return@launch
            TLog.d(TAG, result)
            val dataList = result.datas
            _itemIndexData.addAll(dataList)
            isRequesting = false
        }

    }

    //index list end

    fun login(account: String, password: String, callBack: (user: User) -> Unit) {
        viewModelScope.launch {
            val result = requestData {
                val result = MainServiceImp.loginWanAndroid(account, password)
                TLog.d(TAG, "login first $result")
                MainServiceImp.getUserProfileInfo()
            }
            result ?: return@launch
            TLog.d(TAG, "$result")
            User.update(user = result.userInfo)
            callBack(result.userInfo)
        }
    }

    fun logout(callBack: () -> Unit) {
        viewModelScope.launch {
            val result = requestData {
                MainServiceImp.logoutWanAndroid()
            }
            callBack()
            User.clear()
            result ?: return@launch
            TLog.d(TAG, "$result")
        }
    }

}