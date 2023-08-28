package com.example.newdemo.compose

import GlobalComposeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newdemo.core.uitils.SpCenter
import com.example.newdemo.core.uitils.requestData
import com.example.newdemo.model.BannerModel
import com.example.newdemo.model.IndexItem
import com.example.newdemo.model.Tag
import com.example.newdemo.model.User
import com.example.newdemo.repo.MainServiceImp
import com.test.soultools.tool.log.TLog
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class WanMainViewModel : ViewModel() {


    companion object {

        private const val TAG = "WanMainViewModel"

    }

    //index list start

    private val _itemIndexData = mutableStateListOf<IndexItem>()

    private val _bannerData = mutableStateListOf<BannerModel>()

    var bannerDataGlobal = _bannerData

    val itemIndexData: List<IndexItem> = _itemIndexData

    var loadMoreIndex = 0

    private var isRequesting = false

    private var refreshJob: Job? = null

    var theme by mutableStateOf(getCurrentTheme())

    fun refreshIndexListPage() {
        TLog.d(TAG)
        refreshJob?.cancel()
        loadMoreIndex = 0
        _itemIndexData.clear()
        refreshJob = viewModelScope.launch {
            val bannerData = viewModelScope.async {
                MainServiceImp.getBanners()
            }
            val topData = viewModelScope.async {
                MainServiceImp.getTopArticles()
            }
            val firstFreshData = viewModelScope.async {
                MainServiceImp.getIndexArticles(loadMoreIndex)
            }
            val topResult = topData.await().body()?.data ?: return@launch
            val firstResult = firstFreshData.await().body()?.data?.datas ?: return@launch
            val finalResult = ArrayList<IndexItem>(topResult)
            finalResult.forEach {
                it.tags.add(0, Tag("置顶", ""))
            }
            finalResult.addAll(firstResult)
            TLog.d(TAG, finalResult)
            _itemIndexData.addAll(finalResult)
            val banner = bannerData.await().body()?.data
            banner ?: return@launch
            bannerDataGlobal.clear()
            bannerDataGlobal.addAll(banner)
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

    fun register(
        account: String,
        password: String,
        rePassword: String,
        callBack: (user: User) -> Unit
    ) {
        viewModelScope.launch {
            val result = requestData {
                val result = MainServiceImp.registerWanAndroid(account, password, rePassword)
                TLog.d(TAG, "register first $result")
                MainServiceImp.getUserProfileInfo()
            }
            result ?: return@launch
            TLog.d(TAG, "get final register result : $result")
            User.update(user = result.userInfo)
            callBack(result.userInfo)
        }
    }

    private fun getCurrentTheme(): GlobalComposeTheme.Theme {
        return if (SpCenter.DARK_MODE) {
            GlobalComposeTheme.Theme.Dark
        } else {
            GlobalComposeTheme.Theme.Light
        }
    }

    override fun onCleared() {
        super.onCleared()
        TLog.d("vw", "")
    }

}