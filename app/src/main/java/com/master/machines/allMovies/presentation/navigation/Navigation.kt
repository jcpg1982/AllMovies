package com.master.machines.allMovies.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.master.machines.allMovies.data.models.Movie
import com.master.machines.allMovies.data.models.Routes
import com.master.machines.allMovies.presentation.ui.composables.DetailMovie
import com.master.machines.allMovies.presentation.ui.composables.Home
import com.master.machines.allMovies.presentation.ui.composables.Login
import com.master.machines.allMovies.presentation.viewModels.NavigationViewModel

object Navigation {

    @Composable
    fun NavigationMain(
        navController: NavHostController,
        startDestination: String,
        navigationViewModel: NavigationViewModel
    ) {
        NavHost(navController = navController, startDestination = startDestination) {
            composable(route = Routes.Login.route) { Login(navigationViewModel) }
            composable(route = Routes.Home.route,
                enterTransition = { getEnterTransition() },
                exitTransition = { getExitTransition() },
                popEnterTransition = { getEnterTransition() },
                popExitTransition = { getExitTransition() }) { Home(navigationViewModel) }
            composable(route = Routes.DetailMovie().route,
                arguments = listOf(navArgument("movieId") { type = NavType.IntType }),
                enterTransition = { getEnterTransition() },
                exitTransition = { getExitTransition() },
                popEnterTransition = { getEnterTransition() },
                popExitTransition = { getExitTransition() }) { backStackEntry ->
                backStackEntry.arguments?.getInt("movieId")?.let { movie ->
                    DetailMovie(movie)
                }

            }
        }
    }

    private fun getEnterTransition(): EnterTransition {
        val fastOutSlowIn = FastOutSlowInEasing
        return slideInHorizontally(
            initialOffsetX = { 1000 }, animationSpec = tween(700, easing = fastOutSlowIn)
        ) + fadeIn(
            initialAlpha = 0.3f, animationSpec = tween(700, easing = fastOutSlowIn)
        ) + scaleIn(
            initialScale = 0.9f, animationSpec = tween(700, easing = fastOutSlowIn)
        )
    }

    private fun getExitTransition(): ExitTransition {
        val fastOutSlowIn = FastOutSlowInEasing
        return slideOutHorizontally(
            targetOffsetX = { -1000 }, animationSpec = tween(700, easing = fastOutSlowIn)
        ) + fadeOut(
            targetAlpha = 0.3f, animationSpec = tween(700, easing = fastOutSlowIn)
        ) + scaleOut(
            targetScale = 0.9f, animationSpec = tween(700, easing = fastOutSlowIn)
        )
    }
}