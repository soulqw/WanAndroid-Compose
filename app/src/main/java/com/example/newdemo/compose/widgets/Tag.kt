package com.example.newdemo.compose.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tag(tagName: String, color: Color) {
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .border(0.5.dp, color, RoundedCornerShape(2.dp))
            .padding(2.dp, 0.dp)
    ) {
        Text(
            fontSize = 12.sp,
            text = tagName,
            color = color,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}