package com.thanhng224.app.feature.auth.domain.repositories

import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.auth.domain.entities.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(username: String, password: String): Result<User>
    suspend fun logout(): Result<Unit>
    fun isLoggedIn(): Flow<Boolean>
    suspend fun setLoggedIn(isLoggedIn: Boolean)
}

