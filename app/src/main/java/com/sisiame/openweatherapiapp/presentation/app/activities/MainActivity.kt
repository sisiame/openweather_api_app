package com.sisiame.openweatherapiapp.presentation.app.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.sisiame.openweatherapiapp.presentation.ui.components.HomeScreen
import com.sisiame.openweatherapiapp.presentation.ui.theme.OpenWeatherAPIAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OpenWeatherAPIAppTheme {
                HomeScreen()
            }
        }
    }
}
