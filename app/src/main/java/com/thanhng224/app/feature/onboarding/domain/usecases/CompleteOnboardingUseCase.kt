package com.thanhng224.app.feature.onboarding.domain.usecases

import com.thanhng224.app.feature.onboarding.domain.repositories.OnboardingRepository
import javax.inject.Inject

class CompleteOnboardingUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    suspend operator fun invoke() {
        onboardingRepository.setFirstLaunch(false)
    }
}

