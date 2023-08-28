package com.example.newdemo.compose.pages

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.newdemo.compose.RouterDefine
import com.example.newdemo.compose.WanMainViewModel
import com.example.newdemo.compose.widgets.WanToolBar
import com.example.newdemo.core.uitils.CommonUtils
import com.test.soultools.tool.log.TLog

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RegisterScreen(nvController: NavHostController) {
    val viewModel: WanMainViewModel = viewModel()
    TLog.d("qw", viewModel)
    Scaffold(
        backgroundColor = GlobalComposeTheme.colors.background,
        modifier = Modifier.fillMaxSize(),
        topBar = {
            WanToolBar(title = "Sign up", icon = Icons.Default.ArrowBack) {
                nvController.popBackStack()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            var name by remember {
                mutableStateOf("")
            }
            var password by remember {
                mutableStateOf("")
            }
            var rePassword by remember {
                mutableStateOf("")
            }
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = GlobalComposeTheme.colors.primaryText,
                    unfocusedBorderColor = GlobalComposeTheme.colors.secondary,
                    unfocusedLabelColor = GlobalComposeTheme.colors.secondary
                ),
                value = name,
                onValueChange = {
                    name = it.trim()
                },
                label = { Text(text = "Account") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = GlobalComposeTheme.colors.primaryText,
                    unfocusedBorderColor = GlobalComposeTheme.colors.secondary,
                    unfocusedLabelColor = GlobalComposeTheme.colors.secondary
                ),
                value = password,
                onValueChange = {
                    password = it.trim()
                },
                label = { Text(text = "Password") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = GlobalComposeTheme.colors.primaryText,
                    unfocusedBorderColor = GlobalComposeTheme.colors.secondary,
                    unfocusedLabelColor = GlobalComposeTheme.colors.secondary
                ),
                value = rePassword,
                onValueChange = {
                    rePassword = it.trim()
                },
                label = { Text(text = "RePassword") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    if (name.isBlank() || password.isEmpty() || rePassword.isEmpty()) {
                        CommonUtils.shortToast("账号或密码为空")
                        return@Button
                    }
                    if (password != rePassword) {
                        CommonUtils.shortToast("两次密码输入不一致，请检查")
                        return@Button
                    }
                    viewModel.register(name, password, rePassword) {
                        CommonUtils.shortToast("Sign up Success!")
                        nvController.popBackStack(RouterDefine.HOME, false)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Sign Up")
            }
        }
    }
}
