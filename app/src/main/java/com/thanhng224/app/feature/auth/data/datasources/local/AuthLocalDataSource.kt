package com.thanhng224.app.feature.auth.data.datasources.local

import com.thanhng224.app.core.data.local.AppPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(
    private val appPreferences: AppPreferences
) {
    fun isLoggedIn(): Flow<Boolean> {
        return appPreferences.isLoggedIn
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean) {
        appPreferences.setLoggedIn(isLoggedIn)
    }
}

