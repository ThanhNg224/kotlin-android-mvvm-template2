package com.thanhng224.app.feature.auth.data.datasources.remote

import com.thanhng224.app.feature.auth.domain.entities.User
import javax.inject.Inject

/**
 * Handles authentication calls. Replace the dummy check with a real API integration.
 */
class AuthRemoteDataSource @Inject constructor() {

    fun login(username: String, password: String): User {
        // Dummy authentication - swap with Retrofit call or SDK integration
        if (username == "admin" && password == "1234") {
            return User(username = username, isLoggedIn = true)
        } else {
            throw IllegalArgumentException("Invalid username or password")
        }
    }
}
