package com.example.newdemo.compose.widgets

import GlobalComposeTheme
import androidx.annotation.DrawableRes
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
        color = GlobalComposeTheme.colors.divider,
        thickness = 0.5.dp
    )
    Row() {
        TabItem(com.example.newdemo.R.drawable.ic_home_black_24dp, "首页",
            if (selected == 0) GlobalComposeTheme.colors.primary else GlobalComposeTheme.colors.secondary,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(0)
                }
        )
        TabItem(
            com.example.newdemo.R.drawable.ic_apps_black_24dp, "体系",
            if (selected == 1) GlobalComposeTheme.colors.primary else GlobalComposeTheme.colors.secondary,
            Modifier
                .weight(1f)
                .clickable {
                    onSelectedChanged(1)
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
