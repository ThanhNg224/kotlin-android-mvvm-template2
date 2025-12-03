package com.thanhng224.app.feature.auth.data.repositories

import com.thanhng224.app.core.di.IoDispatcher
import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.auth.data.datasources.local.AuthLocalDataSource
import com.thanhng224.app.feature.auth.data.datasources.remote.AuthRemoteDataSource
import com.thanhng224.app.feature.auth.domain.entities.User
import com.thanhng224.app.feature.auth.domain.repositories.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localDataSource: AuthLocalDataSource,
    private val remoteDataSource: AuthRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override suspend fun login(username: String, password: String): Result<User> {
        return withContext(ioDispatcher) {
            try {
                val user = remoteDataSource.login(username, password)
                localDataSource.setLoggedIn(true)
                Result.Success(user)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override suspend fun logout(): Result<Unit> {
        return withContext(ioDispatcher) {
            try {
                localDataSource.setLoggedIn(false)
                Result.Success(Unit)
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return localDataSource.isLoggedIn()
    }

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        withContext(ioDispatcher) {
            localDataSource.setLoggedIn(isLoggedIn)
        }
    }
}

