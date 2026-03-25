package br.me.vitorcsouza.jobfydev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import br.me.vitorcsouza.jobfydev.ui.presentation.home.HomeScreen
import br.me.vitorcsouza.jobfydev.ui.presentation.splash.SplashScreen
import br.me.vitorcsouza.jobfydev.ui.theme.JobfyDevTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        setContent {
            var showSplash by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(3000)
                showSplash = false
            }

            JobfyDevTheme(darkTheme = false) {
                if (showSplash) {
                    SplashScreen()
                } else {
                    HomeScreen()
                }
            }
        }
    }
}
