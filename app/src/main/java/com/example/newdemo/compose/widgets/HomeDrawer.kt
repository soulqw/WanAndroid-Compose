package com.example.newdemo.compose.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newdemo.compose.RouterDefine
import com.example.newdemo.model.User

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DrawerContent(nvController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (User.alreadyLogin()) {
                    nvController.navigate(RouterDefine.USER_PROFILE)
                } else {
                    nvController.navigate(RouterDefine.LOGIN)
                }
            }) {
            Icon(
                imageVector = Icons.Filled.AccountBox,
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(80.dp)
                    .padding(horizontal = 16.dp)
            )
            Text(
                text = if (User.alreadyLogin()) {
                    User.current.getShowName()
                } else {
                    "未登录"
                },
                color = Color.Black,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
        }
        Divider(
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Column() {
            ListItem(
                text = { Text(text = "设置") },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = ""
                    )
                },
                modifier = Modifier.clickable(onClick = {
                    nvController.navigate(RouterDefine.SETTINGS)
                })
            )
        }
    }
}
