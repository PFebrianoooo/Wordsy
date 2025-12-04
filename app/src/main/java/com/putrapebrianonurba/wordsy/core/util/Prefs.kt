package com.putrapebrianonurba.wordsy.core.util

import android.content.SharedPreferences
import javax.inject.Inject

class Prefs @Inject constructor(
    private val prefs: SharedPreferences
) {
    companion object {
        const val KEY_ONBOARDING_DONE = "onboarding_done"
    }

    fun isOnboardingDone(): Boolean =
        prefs.getBoolean(KEY_ONBOARDING_DONE, false)

    fun setOnboardingDone() {
        prefs.edit().putBoolean(KEY_ONBOARDING_DONE, true).apply()
    }
}
