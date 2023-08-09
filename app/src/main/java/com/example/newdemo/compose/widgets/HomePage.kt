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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.newdemo.compose.WanMainViewModel
import com.example.newdemo.model.BannerModel
import com.example.newdemo.model.IndexItem
import com.test.soultools.tool.log.TLog
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainPage(
    nvController: NavHostController,
    onBannerClick: (item: BannerModel) -> Unit,
    onItemClick: (index: Int, item: IndexItem) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val viewModel: WanMainViewModel = viewModel()
    viewModel.refreshIndexListPage()
    TLog.d("qw", viewModel)
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
                        IndexPage(
                            banners = viewModel.bannerDataGlobal,
                            articles = viewModel.itemIndexData,
                            onBannerClick = onBannerClick,
                            onItemClick = onItemClick
                        )
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