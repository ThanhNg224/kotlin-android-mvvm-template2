package com.thanhng224.app.feature.onboarding.data.repositories

import com.thanhng224.app.feature.onboarding.data.datasources.local.OnboardingLocalDataSource
import com.thanhng224.app.feature.onboarding.domain.repositories.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val localDataSource: OnboardingLocalDataSource
) : OnboardingRepository {

    override fun isFirstLaunch(): Flow<Boolean> {
        return localDataSource.isFirstLaunch()
    }

    override suspend fun setFirstLaunch(isFirstLaunch: Boolean) {
        localDataSource.setFirstLaunch(isFirstLaunch)
    }
}

