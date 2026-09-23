package com.thanhng224.app.feature.auth.domain.usecases

import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.auth.domain.entities.User
import com.thanhng224.app.feature.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<User> {
        val validationError = when {
            username.isBlank() -> "Username cannot be empty"
            password.isBlank() -> "Password cannot be empty"
            password.length < MIN_PASSWORD_LENGTH -> "Password must be at least $MIN_PASSWORD_LENGTH characters"
            else -> null
        }

        if (validationError != null) {
            return Result.Error(IllegalArgumentException(validationError))
        }

        return authRepository.login(username, password)
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 4
    }
}

