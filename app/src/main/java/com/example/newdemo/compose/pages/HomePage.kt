package com.example.newdemo.compose.pages

import GlobalComposeTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.newdemo.compose.commom.RouterDefine
import com.example.newdemo.compose.viewmodel.WanMainViewModel
import com.example.newdemo.compose.widgets.BottomBar
import com.example.newdemo.compose.widgets.WanToolBar
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
            WanToolBar(title = "WanAndroid", icon = Icons.Filled.Menu, actions = {
                IconButton(
                    onClick = {
                        nvController.navigate(RouterDefine.SEARCH)
                    }
                ) {
                    Icon(Icons.Filled.Search, contentDescription = "search", tint = Color.White)
                }
            }) {
                scope.launch {
                    scaffoldState.drawerState.open()
                }
            }
        },
        drawerContent = {
            DrawerContent(nvController)
        }
    ) {
        Column(modifier = Modifier.background(GlobalComposeTheme.colors.background)) {
            val pagerState = rememberPagerState()
            HorizontalPager(
                pageCount = 2,
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