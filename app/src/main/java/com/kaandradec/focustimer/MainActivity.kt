package com.kaandradec.focustimer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.Modifier
import com.kaandradec.focustimer.presentation.home.HomeScreen
import com.kaandradec.focustimer.presentation.home.HomeScreenViewModel
import com.kaandradec.focustimer.presentation.theme.FocusTimerTheme
import dagger.hilt.android.AndroidEntryPoint

// internal val viewModel: HomeScreenViewModel = HomeScreenViewModel()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val windowSize = calculateWindowSizeClass(this)
            FocusTimerTheme(
                windowSize = windowSize.widthSizeClass
            ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Surface(modifier = Modifier.padding(innerPadding)) {
                        // HomeScreen(viewModel)
                        HomeScreen()
                    }
                }
            }
        }
    }
}

