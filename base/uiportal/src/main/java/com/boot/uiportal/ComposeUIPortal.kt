package com.boot.uiportal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import com.boot.uiportal.ui.components.MultiFloatingActionButton
import com.boot.uiportal.ui.theme.BootAndroidTheme

class ComposeUIPortal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BootAndroidTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MultiFloatingActionButton()
                }
            }
        }
    }
}