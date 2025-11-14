package com.thanhng224.app.feature.onboarding.domain.usecases

import com.thanhng224.app.feature.onboarding.domain.repositories.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CheckFirstLaunchUseCase @Inject constructor(
    private val onboardingRepository: OnboardingRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return onboardingRepository.isFirstLaunch()
    }
}

