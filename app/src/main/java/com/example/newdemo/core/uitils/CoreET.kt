package com.example.newdemo.core.uitils

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.newdemo.AppApplication
import com.example.newdemo.model.BusinessResp
import retrofit2.Response
import java.lang.IllegalStateException

suspend fun <T> ViewModel.requestData(requestBody: suspend () -> Response<BusinessResp<T>>): T? {
    try {
        val body = requestBody().body() ?: throw IllegalStateException("bad request")
        if (body.errorCode == -1) {
            throw IllegalStateException(body.errorMsg)
        }
        return body.data
    } catch (e: Exception) {
        Toast.makeText(AppApplication.getInstance(), e.message, Toast.LENGTH_SHORT).show()
    }
    return null
}