package com.example.newdemo.compose.pages

import GlobalComposeTheme
import androidx.compose.foundation.background
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
            .background(GlobalComposeTheme.colors.background)
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
                    .padding(horizontal = 16.dp),
                tint = GlobalComposeTheme.colors.primaryText
            )
            Text(
                text = if (User.alreadyLogin()) {
                    User.current.getShowName()
                } else {
                    "未登录"
                },
                color = GlobalComposeTheme.colors.primaryText,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 16.dp, top = 16.dp)
            )
        }
        Divider(
            color = GlobalComposeTheme.colors.divider,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Column() {
            ListItem(
                text = { Text(text = "设置", color = GlobalComposeTheme.colors.primaryText) },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "",
                        tint = GlobalComposeTheme.colors.primaryText
                    )
                },
                modifier = Modifier.clickable(onClick = {
                    nvController.navigate(RouterDefine.SETTINGS)
                })
            )
        }
    }
}
