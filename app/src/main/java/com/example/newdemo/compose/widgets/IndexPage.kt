package com.example.newdemo.compose.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    onBannerClick(banners[page])
                }) {
                run {
                    AsyncImage(
                        model = banners[page].imagePath,
                        contentDescription = banners[page].desc,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                if (banners[page].desc.isNotBlank()) {
                    Text(
                        text = banners[page].desc,
                        color = Color.White,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .fillMaxWidth()
                            .background(color = Color(0x66000000))
                            .padding(4.dp)
                    )
                }
            }
        }
        Divider(
            color = Color.LightGray,
            thickness = 0.5.dp
        )
        IndexList(articles = articles, onItemClick = onItemClick)
    }
}