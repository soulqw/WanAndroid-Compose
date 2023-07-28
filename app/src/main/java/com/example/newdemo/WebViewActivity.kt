package com.example.newdemo

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.MenuItem
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.test.soultools.tool.log.TLog

class WebViewActivity : AppCompatActivity() {

    companion object {

        private const val TAG = "WebViewActivity"

        private const val URL = "url_extra"

        fun start(context: Context, url: String) {
            Intent(context, WebViewActivity::class.java).apply {
                putExtra(URL, url)
                if ((context is Activity).not()) {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(this)
            }
        }
    }

    private lateinit var web: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        initTitleBar()
        web = findViewById(R.id.web_view)
        val url = intent.extras?.getString(URL)
        TLog.d(TAG, url)
        initWebSettings(url)
        if (url.isNullOrBlank()) {
            Toast.makeText(this, "bad url desc", Toast.LENGTH_SHORT).show()
            finish()
        } else {
            web.loadUrl(url)
        }
    }

    private fun initTitleBar() {
        val toolbar: Toolbar = findViewById(R.id.web_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun initWebSettings(url: String?) {
        url ?: return
        web.settings.apply {
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
            javaScriptEnabled = true
            builtInZoomControls = true
            displayZoomControls = false
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            useWideViewPort = true
            loadWithOverviewMode = true
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            blockNetworkImage = false
        }
        web.webViewClient = object : WebViewClient() {
            override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                title = view?.title ?: "Mobile Web"
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        web.destroy()
    }

}