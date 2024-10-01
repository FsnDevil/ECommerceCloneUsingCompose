package com.example.ecommerceclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ecommerceclone.ui.screens.App
import com.example.ecommerceclone.ui.theme.ECommerceCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECommerceCloneTheme {
                App()
            }
        }
    }
}