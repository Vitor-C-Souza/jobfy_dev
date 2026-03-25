package br.me.vitorcsouza.jobfydev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import br.me.vitorcsouza.jobfydev.ui.presentation.NavGraph
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
        hideSystemUI()
        
        setContent {
            var showSplash by remember { mutableStateOf(true) }
            val navController = rememberNavController()

            LaunchedEffect(Unit) {
                delay(3000)
                showSplash = false
            }

            JobfyDevTheme(darkTheme = false) {
                if (showSplash) {
                    SplashScreen()
                } else {
                    NavGraph(navController = navController)
                }
            }
        }
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
