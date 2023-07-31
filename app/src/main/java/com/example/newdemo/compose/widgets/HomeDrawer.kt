package com.example.newdemo.compose.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrawerContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Menu,
            contentDescription = "Avatar",
            modifier = Modifier
                .size(80.dp)
                .padding(horizontal = 16.dp)
        )
        Text(
            text = "User Name",
            color = Color.Black,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        Divider(
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Column() {
            ListItem(
                text = { Text(text = "item") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                },
                modifier = Modifier.clickable(onClick = {
                    // TODO: Handle click event
                })
            )
        }
    }
}
