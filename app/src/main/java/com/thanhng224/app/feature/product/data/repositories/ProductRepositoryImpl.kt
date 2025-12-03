package com.thanhng224.app.feature.product.data.repositories

import com.thanhng224.app.core.di.IoDispatcher
import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.product.data.datasources.remote.ProductRemoteDataSource
import com.thanhng224.app.feature.product.data.models.mappers.toEntity
import com.thanhng224.app.feature.product.domain.entities.ProductDetails
import com.thanhng224.app.feature.product.domain.repositories.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {

    override suspend fun getProductDetails(): Result<ProductDetails> {
        return withContext(ioDispatcher) {
            try {
                val dto = remoteDataSource.getProductDetails()
                Result.Success(dto.toEntity())
            } catch (e: Exception) {
                Result.Error(e)
            }
        }
    }
}

