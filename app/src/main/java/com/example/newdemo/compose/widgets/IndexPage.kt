package com.example.newdemo.compose.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.newdemo.model.BannerModel
import com.example.newdemo.model.IndexItem
import com.test.soultools.tool.log.TLog
import kotlinx.coroutines.delay

@ExperimentalFoundationApi
@Composable
fun IndexPage(
    banners: List<BannerModel>,
    articles: List<IndexItem>,
    onBannerClick: (item: BannerModel) -> Unit,
    onItemClick: (index: Int, item: IndexItem) -> Unit
) {
    Column(Modifier.fillMaxWidth()) {
        val pagerState = rememberPagerState()
        LaunchedEffect(Unit) {
            while (true) {
                delay(3000)
                pagerState.animateScrollToPage((pagerState.currentPage + 1) % banners.size)
            }
        }
        HorizontalPager(pageCount = banners.size, state = pagerState) { page ->
            TLog.d("qw", page)
            run {
                AsyncImage(
                    model = banners[page].imagePath,
                    contentDescription = banners[page].desc,
                    modifier = Modifier.clickable {
                        onBannerClick(banners[page])
                    }
                )
            }
        }
        Divider(
            color = Color.LightGray,
            thickness = 0.5.dp
        )
        IndexList(articles = articles, onItemClick = onItemClick)
    }
}