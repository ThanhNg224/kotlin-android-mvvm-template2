package com.thanhng224.app.feature.auth.domain.usecases

import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.auth.domain.entities.User
import com.thanhng224.app.feature.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<User> {
        // Validation logic
        if (username.isBlank()) {
            return Result.Error(IllegalArgumentException("Username cannot be empty"))
        }

        if (password.isBlank()) {
            return Result.Error(IllegalArgumentException("Password cannot be empty"))
        }

        // Business rule: minimum password length
        if (password.length < 4) {
            return Result.Error(IllegalArgumentException("Password must be at least 4 characters"))
        }

        return authRepository.login(username, password)
    }
}

