package com.thanhng224.app.feature.onboarding.domain.repositories

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    fun isFirstLaunch(): Flow<Boolean>
    suspend fun setFirstLaunch(isFirstLaunch: Boolean)
}

