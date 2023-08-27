package com.example.newdemo.compose.pages

import GlobalComposeTheme
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.newdemo.compose.widgets.WanToolBar
import com.example.newdemo.core.uitils.SpCenter

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsPanel(nvController: NavHostController) {
    var isChecked by remember { mutableStateOf(SpCenter.DARK_MODE) }
    Scaffold(
        topBar = {
            WanToolBar(title = "Settings", icon = Icons.Default.ArrowBack) {
                nvController.popBackStack()
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GlobalComposeTheme.colors.background)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Dark Mode",
                        color = GlobalComposeTheme.colors.primaryText,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                    )
                    Switch(
                        checked = isChecked,
                        onCheckedChange = {
                            isChecked = it
                            SpCenter.DARK_MODE = it
                        }
                    )
                }
                Divider(
                    color = GlobalComposeTheme.colors.divider,
                    thickness = 0.5.dp
                )
            }
        }
    )
}





