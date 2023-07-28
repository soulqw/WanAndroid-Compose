package com.example.newdemo.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import com.example.newdemo.WebViewActivity
import com.example.newdemo.compose.widgets.IndexList
import com.example.newdemo.compose.widgets.WanToolBar
import com.test.soultools.tool.log.TLog

class WanAndroidActivity : ComponentActivity() {

    private val viewModel: WanMainViewModel by lazy {
        ViewModelProvider(this@WanAndroidActivity)[WanMainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initComponent()
        viewModel.refreshIndexArticle {
            setContent {
                Column {
                    Toolbar()
                    IndexList(articles = viewModel.itemIndexData) { _, article ->
                        viewModel.openArticleDetail(article)
                    }
                }
            }
        }
    }

    private fun initComponent() {
        setContent {
            Column {
                Toolbar()
            }
        }
        viewModel.onClickData.observe(this) {
            WebViewActivity.start(applicationContext, it.link)
        }
    }

    @Composable
    fun Toolbar() {
        WanToolBar(title = "WanAndroid") {
            TLog.d("qw", "onBackPressed")
            this@WanAndroidActivity.finish()
        }
    }
}