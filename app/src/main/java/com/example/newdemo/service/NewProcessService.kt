package com.example.newdemo.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.newdemo.ITestServer.Stub

class NewProcessService : Service() {

    companion object {
        const val TAG = "qw"
    }

    private val bindStub by lazy {
        object : Stub() {
            override fun sayData(sayConetent: String?) {
                Log.d("qw", "get data from client$sayConetent")
            }

            override fun getData(input: String?): String {
                Log.d("qw", "get data from client$input and return ok")
                return "$input ok"
            }

        }
    }

    override fun onBind(p0: Intent?): IBinder? {
        return bindStub
    }
}