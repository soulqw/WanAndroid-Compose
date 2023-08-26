package com.example.newdemo.compose.widgets

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun WanToolBar(
    title: String,
    icon: ImageVector,
    actions: @Composable RowScope.() -> Unit = {},
    onLeftClick: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = {
                onLeftClick()
            }) {
                Icon(icon, contentDescription = "")
            }
        },
        actions = actions
    )
}