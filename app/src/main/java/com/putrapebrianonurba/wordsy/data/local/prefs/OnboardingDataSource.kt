package com.putrapebrianonurba.wordsy.data.local.prefs

import com.putrapebrianonurba.wordsy.core.util.Prefs
import javax.inject.Inject

class OnboardingDataSource @Inject constructor(
    private val prefs: Prefs
) {
    suspend fun isFirstLaunch(): Boolean =
        !prefs.isOnboardingDone()

    suspend fun completeOnboarding() =
        prefs.setOnboardingDone()

    // Synchronous version
    fun isOnboardingDoneSync(): Boolean =
        prefs.isOnboardingDone()
}
