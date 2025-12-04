package com.putrapebrianonurba.wordsy.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.putrapebrianonurba.wordsy.domain.repository.OnboardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingScreenViewModel @Inject constructor(
    private val onboardingRepository: OnboardingRepository
): ViewModel() {
    fun completeOnboarding() {
        viewModelScope.launch {
            onboardingRepository.completeOnboarding()
        }
    }
}