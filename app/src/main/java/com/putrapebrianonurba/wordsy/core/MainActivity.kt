package com.putrapebrianonurba.wordsy.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.putrapebrianonurba.wordsy.domain.repository.OnboardingRepository
import com.putrapebrianonurba.wordsy.presentation.navigation.WordsyNavHost
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.GraphRoute
import com.putrapebrianonurba.wordsy.presentation.notes.main.NotesMainScreen
import com.putrapebrianonurba.wordsy.ui.theme.WordsyTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var onboardingRepository: OnboardingRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(
                Color.White.hashCode(),
                Color.White.hashCode()
            ),
            navigationBarStyle = SystemBarStyle.light(
                Color(0xFFEDEDED).hashCode(),
                Color(0xFFEDEDED).hashCode()
            )
        )

        val isFirstLaunch = onboardingRepository.isOnboardingDoneSync()
        setContent {
            val startDestination = if (isFirstLaunch) GraphRoute.NOTES else GraphRoute.ONBOARDING

            WordsyNavHost(startDestination)
        }
    }
}
