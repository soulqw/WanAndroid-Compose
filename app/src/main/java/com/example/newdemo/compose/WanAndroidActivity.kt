package com.example.newdemo.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newdemo.WebViewActivity
import com.example.newdemo.compose.widgets.LoginScreen
import com.example.newdemo.compose.widgets.MainPage

class WanAndroidActivity : ComponentActivity() {

    companion object {
        private const val TAG = "WanAndroidActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
//            MainPage(nvController = navController, viewModel)
            NavHost(navController, startDestination = RouterDefine.HOME) {
                composable(RouterDefine.HOME) {
                    MainPage(nvController = navController) { _, item ->
                        WebViewActivity.start(applicationContext, item.link)
                    }
                }
                composable(RouterDefine.LOGIN) {
                    LoginScreen(
                        nvController = navController
                    )
                }
            }
        }
    }

}