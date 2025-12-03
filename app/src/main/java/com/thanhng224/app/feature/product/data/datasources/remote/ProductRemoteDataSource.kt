package com.thanhng224.app.feature.product.data.datasources.remote

import com.thanhng224.app.feature.product.data.models.ProductDetailsDto
import javax.inject.Inject

class ProductRemoteDataSource @Inject constructor(
    private val apiService: ProductApiService
) {
    suspend fun getProductDetails(): ProductDetailsDto {
        return apiService.getProductDetails()
    }
}

