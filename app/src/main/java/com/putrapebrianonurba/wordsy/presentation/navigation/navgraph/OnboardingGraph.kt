package com.putrapebrianonurba.wordsy.presentation.navigation.navgraph

import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.GraphRoute
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.OnboardingScreen
import com.putrapebrianonurba.wordsy.presentation.onboarding.OnboardingScreen
import com.putrapebrianonurba.wordsy.presentation.onboarding.OnboardingScreenViewModel

fun NavGraphBuilder.onboardingGraph(navController: NavController) {
    navigation(startDestination = OnboardingScreen.Intro.route, route = GraphRoute.ONBOARDING) {
        composable(route = OnboardingScreen.Intro.route) {
            val viewModel: OnboardingScreenViewModel = hiltViewModel()
            OnboardingScreen(navController, viewModel)
        }
    }
}