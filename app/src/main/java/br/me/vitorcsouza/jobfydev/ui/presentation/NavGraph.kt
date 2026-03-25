package br.me.vitorcsouza.jobfydev.ui.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.me.vitorcsouza.jobfydev.ui.presentation.details.JobDetailsScreen
import br.me.vitorcsouza.jobfydev.ui.presentation.home.HomeScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{jobId}") {
        fun createRoute(jobId: Long) = "details/$jobId"
    }
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route) {
            HomeScreen(
                onJobClick = { jobId ->
                    navController.navigate(Screen.Details.createRoute(jobId))
                }
            )
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(
                navArgument("jobId") {
                    type = NavType.LongType
                }
            )
        ) {
            JobDetailsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onShareClick = { /* Implement share */ },
                onBookmarkClick = { /* Implement bookmark */ }
            )
        }
    }
}
