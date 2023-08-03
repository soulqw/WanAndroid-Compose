package com.example.newdemo.compose

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newdemo.core.uitils.requestData
import com.example.newdemo.model.IndexItem
import com.example.newdemo.repo.MainServiceImp
import com.example.newdemo.repo.User
import com.test.soultools.tool.log.TLog
import kotlinx.coroutines.launch


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

    fun refreshIndexArticle() {
        loadMoreIndex = 0
        _itemIndexData.clear()
        viewModelScope.launch {
            val result = requestData {
                MainServiceImp.getIndexArticles(loadMoreIndex)
            } ?: return@launch
            TLog.d(TAG, result)
            val dataList = result.datas
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
                MainServiceImp.loginWanAndroid(account, password)
            }
            result ?: return@launch
            TLog.d(TAG, "$result")
            val user = User(result.username, result.id)
            User.update(user = user)
            callBack(user)
        }
    }

    fun logout(callBack: () -> Unit) {
        viewModelScope.launch {
            val result = requestData {
                MainServiceImp.logoutWanAndroid()
            }
            callBack()
            result ?: return@launch
            TLog.d(TAG, "$result")
        }
    }

}