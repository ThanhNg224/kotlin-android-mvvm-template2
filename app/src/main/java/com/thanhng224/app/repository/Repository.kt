package com.thanhng224.app.repository

import com.thanhng224.app.model.ProductDetailsDto
import com.thanhng224.app.util.ApiState
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun getProductDetails(): ApiState<ProductDetailsDto>

    // Auth & Preferences
    fun isFirstLaunch(): Flow<Boolean>
    suspend fun setFirstLaunch(isFirstLaunch: Boolean)
    fun isLoggedIn(): Flow<Boolean>
    suspend fun setLoggedIn(isLoggedIn: Boolean)
}