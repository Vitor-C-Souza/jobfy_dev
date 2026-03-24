package br.me.vitorcsouza.jobifydev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import br.me.vitorcsouza.jobifydev.ui.main.MainScreen
import br.me.vitorcsouza.jobifydev.ui.splash.SplashScreen
import br.me.vitorcsouza.jobifydev.ui.theme.JobifyDevTheme
import kotlinx.coroutines.delay

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

            JobifyDevTheme {
                if (showSplash) {
                    SplashScreen()
                } else {
                    MainScreen()
                }
            }
        }
    }
}
