package com.thanhng224.app.feature.product.domain.repositories

import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.product.domain.entities.ProductDetails

interface ProductRepository {
    suspend fun getProductDetails(): Result<ProductDetails>
}

