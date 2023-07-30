package com.example.newdemo.compose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.newdemo.WebViewActivity
import com.example.newdemo.compose.widgets.BottomBar
import com.example.newdemo.compose.widgets.IndexList
import com.test.soultools.tool.log.TLog
import kotlinx.coroutines.launch

class WanAndroidActivity : ComponentActivity() {

    companion object {
        private const val TAG = "WanAndroidActivity"
    }

    private val viewModel: WanMainViewModel by lazy {
        ViewModelProvider(this@WanAndroidActivity)[WanMainViewModel::class.java]
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
        viewModel.refreshIndexArticle()
        setContent {
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = { Text(text = "WanAndroid") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }) {
                                Icon(Icons.Filled.Menu, contentDescription = "back")
                            }
                        },
                    )
                },
                drawerContent = {
                    Text("Drawer title", modifier = Modifier.padding(16.dp))
                    Divider()
                    // Drawer items
                }
            ) {
                Column {
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
        viewModel.onClickData.observe(this) {
            WebViewActivity.start(applicationContext, it.link)
        }
    }

}