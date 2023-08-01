package com.example.newdemo.compose.widgets

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.newdemo.compose.WanMainViewModel
import com.test.soultools.tool.log.TLog
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainPage(nvController: NavHostController, viewModel: WanMainViewModel) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            WanToolBar(title = "WanAndroid", icon = Icons.Filled.Menu) {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerContent = {
            DrawerContent(nvController)
        }
    ) {
        Column {
            val pagerState = rememberPagerState()
            HorizontalPager(
                pageCount = 5,
                modifier = Modifier.weight(1f),
                state = pagerState,
                userScrollEnabled = false
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
                TLog.d("qw", it)
                scope.launch {
                    pagerState.scrollToPage(it)
                }
            }
        }
    }
}