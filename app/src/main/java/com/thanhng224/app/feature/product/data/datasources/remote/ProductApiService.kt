package com.thanhng224.app.feature.product.data.datasources.remote

import com.thanhng224.app.feature.product.data.models.ProductDetailsDto
import retrofit2.http.GET

interface ProductApiService {
    @GET("products/1")
    suspend fun getProductDetails(): ProductDetailsDto
}

