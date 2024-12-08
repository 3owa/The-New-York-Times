package com.newyorktimes.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.newyorktimes.features.news.presentation.theme.TheNewYorkTimesTheme
import com.newyorktimes.navigation.AppNavigation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TheNewYorkTimesTheme {

                val navController = rememberNavController()

                AppNavigation(
                    navController = navController
                )

            }
        }
    }
}
