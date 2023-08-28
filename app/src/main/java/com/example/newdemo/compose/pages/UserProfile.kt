package com.example.newdemo.compose.pages

import GlobalComposeTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.newdemo.compose.WanMainViewModel
import com.example.newdemo.compose.widgets.WanToolBar
import com.example.newdemo.model.User

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PersonalDetailsScreen(nvController: NavHostController) {
    val viewModel: WanMainViewModel = viewModel()
    Scaffold(
        topBar = {
            WanToolBar(title = "UserCenter", icon = Icons.Default.ArrowBack) {
                nvController.popBackStack()
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GlobalComposeTheme.colors.background)
                    .padding(16.dp)
            ) {
                Icon(
                    tint = GlobalComposeTheme.colors.primaryText,
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "Avatar",
                    modifier = Modifier
                        .size(120.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = User.current.getShowName(),
                    style = MaterialTheme.typography.h5,
                    color = GlobalComposeTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = User.current.nickname,
                    style = MaterialTheme.typography.subtitle1,
                    color = GlobalComposeTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = User.current.coinCount.toString(),
                    style = MaterialTheme.typography.subtitle1,
                    color = GlobalComposeTheme.colors.primaryText
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        viewModel.logout {
                            nvController.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Logout")
                }
            }
        }
    )
}
