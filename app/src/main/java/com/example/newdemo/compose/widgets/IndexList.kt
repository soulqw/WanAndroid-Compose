package com.example.newdemo.compose.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newdemo.compose.WanMainViewModel
import com.example.newdemo.model.IndexItem
import com.test.soultools.tool.log.TLog

@Composable
fun IndexList(
    status: LazyListState = rememberLazyListState(),
    articles: List<IndexItem>,
    onItemClick: (index: Int, article: IndexItem) -> Unit
) {
    val viewModelWe: WanMainViewModel = viewModel()
    LazyColumn(state = status) {
        itemsIndexed(articles) { index, item ->
            Column(Modifier
                .clickable {
                    onItemClick(index, item)
                }
                .fillMaxWidth()
                .padding(8.dp)) {
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = item.author,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(start = 8.dp)
                    )
                    Text(
                        text = item.niceDate,
                        color = Color.Gray,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    )
                }
                Text(
                    maxLines = 2,
                    text = item.title,
                    color = Color.Black,
                    fontSize = 18.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
                Text(
                    text = item.superChapterName,
                    color = Color.Gray,
                    fontSize = 14.sp,
                )
            }
            if (index < articles.lastIndex) {
                Divider(
                    color = Color.LightGray,
                    thickness = 0.5.dp
                )
            }
            LaunchedEffect(articles.size) {
                if (index == articles.size - 2) {
                    TLog.d("qw", "load more")
                    viewModelWe.loadMoreIndexArticle()
                }
            }
        }
    }

}