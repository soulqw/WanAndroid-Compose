package com.example.newdemo.compose.pages

import GlobalComposeTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newdemo.R
import com.example.newdemo.compose.viewmodel.WanMainViewModel
import com.example.newdemo.compose.widgets.Tag
import com.example.newdemo.model.IndexItem
import com.example.newdemo.model.Tag
import com.test.soultools.tool.log.TLog

@Composable
fun IndexList(
    status: LazyListState = rememberLazyListState(),
    articles: List<IndexItem>,
    onItemClick: (index: Int, item: IndexItem) -> Unit,
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
                    Row(
                        Modifier
                            .fillMaxWidth()
                    ) {
                        LazyRow(
                            Modifier.align(Alignment.CenterVertically)
                        ) {
                            itemsIndexed(item.tags) { _: Int, item: Tag ->
                                Tag(
                                    item.name, if (item.name == "置顶") {
                                        Color.Red
                                    } else {
                                        Color.Blue
                                    }
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                            }
                        }
                        Text(
                            text = item.author,
                            color = GlobalComposeTheme.colors.secondary,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Text(
                        text = item.niceDate,
                        color = GlobalComposeTheme.colors.secondary,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                    )
                }
                Text(
                    maxLines = 2,
                    text = item.title,
                    color = GlobalComposeTheme.colors.primaryText,
                    fontSize = 18.sp,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
                )
                Box(Modifier.fillMaxWidth()) {
                    Text(
                        text = item.superChapterName,
                        color = GlobalComposeTheme.colors.secondary,
                        fontSize = 14.sp,
                    )
                    val iconId = if (item.collect) {
                        R.drawable.ic_like
                    } else {
                        R.drawable.ic_like_not
                    }
                    Icon(
                        painterResource(iconId),
                        null,
                        Modifier
                            .size(24.dp)
                            .align(Alignment.CenterEnd),
                        tint = Color.Red
                    )
                }

            }
            if (index < articles.lastIndex) {
                Divider(
                    color = GlobalComposeTheme.colors.divider,
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