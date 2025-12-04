package com.putrapebrianonurba.wordsy.data.repository.prefs

import com.putrapebrianonurba.wordsy.data.local.prefs.OnboardingDataSource
import com.putrapebrianonurba.wordsy.domain.repository.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val local: OnboardingDataSource
) : OnboardingRepository {
    override suspend fun isFirstLaunch() = local.isFirstLaunch()

    override suspend fun completeOnboarding() = local.completeOnboarding()

    override fun isOnboardingDoneSync(): Boolean =
        local.isOnboardingDoneSync()
}
