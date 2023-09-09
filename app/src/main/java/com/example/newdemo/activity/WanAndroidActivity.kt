package com.example.newdemo.activity

import WanAndroidTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newdemo.compose.commom.RouterDefine
import com.example.newdemo.compose.pages.LoginScreen
import com.example.newdemo.compose.pages.MainPage
import com.example.newdemo.compose.pages.PersonalDetailsScreen
import com.example.newdemo.compose.pages.RegisterScreen
import com.example.newdemo.compose.pages.SearchPanel
import com.example.newdemo.compose.pages.SettingsPanel
import com.example.newdemo.compose.viewmodel.WanMainViewModel
import com.test.soultools.tool.log.TLog

class WanAndroidActivity : ComponentActivity() {

    companion object {
        private const val TAG = "WanAndroidActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModelStoreOwner = this@WanAndroidActivity
            val viewModel: WanMainViewModel =
                viewModel(viewModelStoreOwner)
            WanAndroidTheme(viewModel.theme) {
                val navController = rememberNavController().apply {
                    setLifecycleOwner(this@WanAndroidActivity)
                }
                NavHost(navController, startDestination = RouterDefine.HOME) {
                    composable(RouterDefine.HOME) {
                        MainPage(nvController = navController, onBannerClick = {
                            WebViewActivity.start(applicationContext, it.url)
                        }) { _, item ->
                            WebViewActivity.start(applicationContext, item.link)
                        }
                    }
                    composable(RouterDefine.LOGIN) {
                        LoginScreen(
                            nvController = navController
                        )
                    }
                    composable(RouterDefine.REGISTER) {
                        RegisterScreen(
                            nvController = navController
                        )
                    }
                    composable(RouterDefine.USER_PROFILE) {
                        PersonalDetailsScreen(
                            nvController = navController
                        )
                    }
                    composable(RouterDefine.SEARCH) {
                        SearchPanel(
                            nvController = navController
                        )
                    }
                    composable(RouterDefine.SETTINGS) {
                        SettingsPanel(
                            viewModelStoreOwner,
                            nvController = navController
                        )
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TLog.d("vw")
    }

}