package com.putrapebrianonurba.wordsy

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NotesApp: Application() {}

//NavHost(navController, startDestination = "main") {
//    composable("main") { backStackEntry ->
//        val mainVM: MainViewModel = hiltViewModel(backStackEntry)
//        MainScreen(viewModel = mainVM, navController = navController)
//    }
//    composable("add") { backStackEntry ->
//        val addVM: AddViewModel = hiltViewModel(backStackEntry)
//        AddScreen(viewModel = addVM, navController = navController)
//    }
//    composable("detail/{noteId}") { backStackEntry ->
//        val detailVM: DetailViewModel = hiltViewModel(backStackEntry)
//        val noteId = backStackEntry.arguments?.getString("noteId")?.toInt() ?: 0
//        DetailScreen(viewModel = detailVM, noteId = noteId, navController = navController)
//    }
//}
