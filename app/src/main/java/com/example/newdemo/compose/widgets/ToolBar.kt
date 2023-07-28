package com.example.newdemo.compose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.newdemo.R

@Composable
fun WanToolBar(title: String, onBackClick: () -> Unit) {
    Box(
        Modifier
            .background(Color.White)
            .fillMaxWidth()
    ) {
        Row(Modifier.height(48.dp)) {
            Icon(
                painterResource(id = R.drawable.ic_back),
                contentDescription = "",
                Modifier
                    .clickable(onClick = onBackClick)
                    .align(Alignment.CenterVertically)
                    .size(36.dp)
                    .padding(8.dp),
                tint = Color.Black
            )
        }
        Text(text = title, Modifier.align(Alignment.Center), color = Color.Black)
    }
}