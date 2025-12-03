package com.thanhng224.app.feature.auth.domain.usecases

import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.auth.domain.repositories.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        return authRepository.logout()
    }
}

