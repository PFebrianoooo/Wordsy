package com.putrapebrianonurba.wordsy.presentation.navigation.navgraph

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.GraphRoute
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.ToDoScreen
import com.putrapebrianonurba.wordsy.presentation.todo.ToDoMainScreen
import com.putrapebrianonurba.wordsy.presentation.todo.ToDoMainScreenViewModel

fun NavGraphBuilder.todoGraph(navController: NavController) {
    navigation(startDestination = ToDoScreen.Main.route, route = GraphRoute.TODO) {
        composable(
            route = ToDoScreen.Main.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it }, // full kanan
                    animationSpec = tween(
                        durationMillis = 320,
                        easing = LinearOutSlowInEasing // masuk cepat → lambat
                    )
                ) + fadeIn(tween(180))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it / 6 }, // keluar dikit ke kiri (depth effect)
                    animationSpec = tween(
                        durationMillis = 250,
                        easing = FastOutLinearInEasing // lambat → cepat
                    )
                ) + fadeOut(tween(120))
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it / 6 }, // masuk sedikit dari kiri (depth effect)
                    animationSpec = tween(
                        durationMillis = 250,
                        easing = LinearOutSlowInEasing
                    )
                ) + fadeIn(tween(150))
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it }, // full kanan
                    animationSpec = tween(
                        durationMillis = 320,
                        easing = FastOutSlowInEasing // pop-out lembut
                    )
                ) + fadeOut(tween(180))
            }
        ) {
            val viewModel: ToDoMainScreenViewModel = hiltViewModel()
            ToDoMainScreen(navController, viewModel)
        }
    }
}