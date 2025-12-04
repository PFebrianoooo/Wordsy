package com.putrapebrianonurba.wordsy.presentation.navigation.routes

sealed class OnboardingScreen(val route: String) {
    data object Intro : OnboardingScreen("intro_screen")
}

sealed class ToDoScreen(val route: String) {
    data object Main : OnboardingScreen("todo_main_screen")
}

sealed class NotesScreen(val route: String) {
    data object Main : NotesScreen("notes_main")
    data object Add : NotesScreen("notes_add")
    data object Detail : NotesScreen("notes_detail/{noteId}")
}

fun NotesScreen.Detail.buildRoute(noteId: Int) = "notes_detail/$noteId"