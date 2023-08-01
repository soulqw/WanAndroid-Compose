package com.example.newdemo.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.newdemo.WebViewActivity
import com.example.newdemo.compose.widgets.MainPage

class WanAndroidActivity : ComponentActivity() {

    companion object {
        private const val TAG = "WanAndroidActivity"
    }

    private val viewModel: WanMainViewModel by lazy {
        ViewModelProvider(this)[WanMainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
        setContent {
            val navController = rememberNavController().apply {
                setViewModelStore(this@WanAndroidActivity.viewModelStore)
            }
            MainPage(nvController = navController, viewModel)
//            NavHost(navController, startDestination = RouterDefine.HOME) {
//                composable(RouterDefine.HOME) { MainPage(nvController = navController, viewModel) }
//                composable(RouterDefine.LOGIN) { LoginScreen(nvController = navController,viewModel) }
//            }
        }
    }

    private fun initComponent() {
        viewModel.onClickData.observe(this) {
            WebViewActivity.start(applicationContext, it.link)
        }
        viewModel.refreshIndexArticle()
    }

}