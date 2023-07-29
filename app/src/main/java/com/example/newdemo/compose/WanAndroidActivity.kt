package com.example.newdemo.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.newdemo.WebViewActivity
import com.example.newdemo.compose.widgets.BottomBar
import com.example.newdemo.compose.widgets.IndexList
import com.example.newdemo.compose.widgets.WanToolBar
import com.test.soultools.tool.log.TLog
import kotlinx.coroutines.launch

class WanAndroidActivity : ComponentActivity() {

    companion object {
        private const val TAG = "WanAndroidActivity"
    }

    private val viewModel: WanMainViewModel by lazy {
        ViewModelProvider(this@WanAndroidActivity)[WanMainViewModel::class.java]
    }

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
        viewModel.refreshIndexArticle {
            setContent {
                Column {
                    Toolbar()
                    val pagerState = rememberPagerState()
                    HorizontalPager(
                        pageCount = 5,
                        modifier = Modifier.weight(1f),
                        state = pagerState
                    ) { page ->
                        when (page) {
                            0 -> {
                                IndexList(articles = viewModel.itemIndexData)
                            }
                            1 -> {
                                Text(text = "this is wechat")
                            }
                            else -> {
                                Text(text = "extra")
                            }
                        }
                    }
                    val scope = rememberCoroutineScope()
                    BottomBar(pagerState.currentPage) {
                        TLog.d(TAG, it)
                        scope.launch {
                            pagerState.scrollToPage(it)
                        }
                    }
                }
            }
        }
    }

    private fun initComponent() {
        setContent {
            Column {
                Toolbar()
            }
        }
        viewModel.onClickData.observe(this) {
            WebViewActivity.start(applicationContext, it.link)
        }
    }

    @Composable
    fun Toolbar() {
        WanToolBar(title = "WanAndroid") {
            TLog.d(TAG, "onBackPressed")
            this@WanAndroidActivity.finish()
        }
    }

}