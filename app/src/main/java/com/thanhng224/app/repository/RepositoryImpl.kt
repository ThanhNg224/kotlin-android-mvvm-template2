package com.thanhng224.app.repository

import com.thanhng224.app.local.AppPreferences
import com.thanhng224.app.model.ProductDetailsDto
import com.thanhng224.app.network.ApiService
import com.thanhng224.app.util.ApiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appPreferences: AppPreferences
) : Repository {

    override suspend fun getProductDetails(): ApiState<ProductDetailsDto> = try {
        ApiState.Success(apiService.getProductDetails())
    } catch (e: Exception) {
        ApiState.Error(errorMsg = e.message.toString())
    }

    override fun isFirstLaunch(): Flow<Boolean> = appPreferences.isFirstLaunch

    override suspend fun setFirstLaunch(isFirstLaunch: Boolean) {
        appPreferences.setFirstLaunch(isFirstLaunch)
    }

    override fun isLoggedIn(): Flow<Boolean> = appPreferences.isLoggedIn

    override suspend fun setLoggedIn(isLoggedIn: Boolean) {
        appPreferences.setLoggedIn(isLoggedIn)
    }
}