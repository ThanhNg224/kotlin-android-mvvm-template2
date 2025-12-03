package com.thanhng224.app.feature.product.domain.usecases

import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.product.domain.entities.ProductDetails
import com.thanhng224.app.feature.product.domain.repositories.ProductRepository
import javax.inject.Inject

class GetProductDetailsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(): Result<ProductDetails> {
        return productRepository.getProductDetails()
    }
}

