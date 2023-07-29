package com.example.newdemo.compose.widgets

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BottomBar(selected: Int, onSelectedChanged: (Int) -> Unit) {
    Divider(
        color = Color.LightGray,
        thickness = 0.5.dp
    )
    Row(Modifier.background(Color.White)) {
        TabItem(com.example.newdemo.R.drawable.ic_home_black_24dp, "首页",
            if (selected == 0) Color.Black else Color.LightGray,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(0)
                }
        )
        TabItem(
            com.example.newdemo.R.drawable.ic_square_black_24dp,
            "广场",
            if (selected == 1) Color.Black else Color.LightGray,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(1)
                }
        )
        TabItem(
            com.example.newdemo.R.drawable.ic_wechat_black_24dp,
            "公众号",
            if (selected == 2) Color.Black else Color.LightGray,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(2)
                }
        )
        TabItem(
            com.example.newdemo.R.drawable.ic_apps_black_24dp, "体系",
            if (selected == 3) Color.Black else Color.LightGray,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(3)
                }
        )
        TabItem(
            com.example.newdemo.R.drawable.ic_project_black_24dp, "项目",
            if (selected == 4) Color.Black else Color.LightGray,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(4)
                }
        )
    }
}

@Composable
fun TabItem(@DrawableRes iconId: Int, title: String, tint: Color, modifier: Modifier = Modifier) {
    Column(
        modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(painterResource(iconId), title, Modifier.size(24.dp), tint = tint)
        Text(title, fontSize = 11.sp, color = tint)
    }
}
