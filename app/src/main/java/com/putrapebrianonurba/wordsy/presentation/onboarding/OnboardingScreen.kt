package com.putrapebrianonurba.wordsy.presentation.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.putrapebrianonurba.wordsy.R
import com.putrapebrianonurba.wordsy.presentation.navigation.routes.GraphRoute

@Composable
fun OnboardingScreen(navController: NavController, viewModel: OnboardingScreenViewModel) {
    // CONTAINER
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // CONTENT SECTION
        // APP NAME
        Text(
            "Wordsy",
            fontSize = 42.sp,
            color = Color(0xFF313131),
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
        )

        // ILUSTRATION & WELCOMED SENTENCE
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // ILUSTRATION
            Image(
                painterResource(R.drawable.onboarding_ilustration),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            Spacer(modifier = Modifier.height(15.dp))

            // WELCOMED
            Text(
                text = "Selamat Datang di Wordsy!",
                color = Color(0xFF74A7CB),
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(10.dp))

            // SENTENCE
            Text(
                text = "Tulis dengan cepat, simpan rapi idemu, dan temukan kembali catatanmu kapan saja.",
                color = Color(0xFF9DC9DD),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // GET STARTED BUTTON
        OutlinedButton(
            onClick = {
                viewModel.completeOnboarding()
                navController.navigate(route = GraphRoute.NOTES) {
                    popUpTo(GraphRoute.ONBOARDING) {inclusive = true}
                }
            },
            enabled = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(bottom = 10.dp, start = 20.dp, end = 20.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF313131),
                contentColor = Color.White
            ),
            border = BorderStroke(0.dp, Color.Transparent)
        ) {
            Text("Mulai Sekarang", fontSize = 16.sp, fontWeight = FontWeight.Medium)
        }
    }
}