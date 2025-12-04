package com.putrapebrianonurba.wordsy.domain.repository

interface OnboardingRepository {
    suspend fun isFirstLaunch(): Boolean
    suspend fun completeOnboarding()
    fun isOnboardingDoneSync(): Boolean
}
