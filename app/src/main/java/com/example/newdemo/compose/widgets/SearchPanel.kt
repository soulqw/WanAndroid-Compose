package com.example.newdemo.compose.widgets

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SearchPanel(nvController: NavHostController) {
    Scaffold(
        topBar = {
            WanToolBar(title = "Search", icon = Icons.Default.ArrowBack) {
                nvController.popBackStack()
            }
        },
        content = {
            Text(text = "Search")
        }
    )
}
