package com.putrapebrianonurba.wordsy.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.putrapebrianonurba.wordsy.presentation.navigation.navgraph.notesGraph
import com.putrapebrianonurba.wordsy.presentation.navigation.navgraph.onboardingGraph
import com.putrapebrianonurba.wordsy.presentation.navigation.navgraph.todoGraph

@Composable
fun WordsyNavHost(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = startDestination) {
        onboardingGraph(navController)
        notesGraph(navController)
        todoGraph(navController)
    }
}