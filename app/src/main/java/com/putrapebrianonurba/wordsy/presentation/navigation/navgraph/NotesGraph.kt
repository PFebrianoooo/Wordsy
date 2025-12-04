package com.putrapebrianonurba.wordsy.presentation.navigation.navgraph

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.GraphRoute
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.NotesScreen
import com.putrapebrianonurba.wordsy.presentation.notes.add.NotesAddScreen
import com.putrapebrianonurba.wordsy.presentation.notes.add.NotesAddScreenViewModel
import com.putrapebrianonurba.wordsy.presentation.notes.detail.NotesDetailScreen
import com.putrapebrianonurba.wordsy.presentation.notes.detail.NotesDetailScreenViewModel
import com.putrapebrianonurba.wordsy.presentation.notes.main.NotesMainScreen
import com.putrapebrianonurba.wordsy.presentation.notes.main.NotesMainScreenViewModel

fun NavGraphBuilder.notesGraph(navController: NavController) {
    navigation(startDestination = NotesScreen.Main.route, route = GraphRoute.NOTES) {
        composable(route = NotesScreen.Main.route) {
            val viewModel: NotesMainScreenViewModel = hiltViewModel()
            NotesMainScreen(navController, viewModel)
        }

        composable(
            route = NotesScreen.Add.route,
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
            val viewModel: NotesAddScreenViewModel = hiltViewModel()
            NotesAddScreen(navController, viewModel)
        }

        composable(
            route = NotesScreen.Detail.route,
            arguments = listOf( navArgument("noteId") { type = NavType.IntType } ),
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
            val viewModel: NotesDetailScreenViewModel = hiltViewModel()
            NotesDetailScreen(navController, viewModel)
        }
    }
}