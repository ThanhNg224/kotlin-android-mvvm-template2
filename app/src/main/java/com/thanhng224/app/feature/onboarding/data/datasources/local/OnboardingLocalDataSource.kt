package com.thanhng224.app.feature.onboarding.data.datasources.local

import com.thanhng224.app.core.data.local.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingLocalDataSource @Inject constructor(
    private val appPreferences: AppPreferences
) {
    fun isFirstLaunch(): Flow<Boolean> {
        return appPreferences.isFirstLaunch
    }

    suspend fun setFirstLaunch(isFirstLaunch: Boolean) {
        appPreferences.setFirstLaunch(isFirstLaunch)
    }
}

