package com.thanhng224.app.feature.product.data.repositories

import com.thanhng224.app.core.di.IoDispatcher
import com.thanhng224.app.core.util.Result
import com.thanhng224.app.feature.product.data.datasources.local.ProductLocalDataSource
import com.thanhng224.app.feature.product.data.datasources.remote.ProductRemoteDataSource
import com.thanhng224.app.feature.product.data.local.mappers.toCacheEntity
import com.thanhng224.app.feature.product.data.local.mappers.toDomain
import com.thanhng224.app.feature.product.data.models.mappers.toEntity
import com.thanhng224.app.feature.product.domain.entities.ProductDetails
import com.thanhng224.app.feature.product.domain.repositories.ProductRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource,
    @param:IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ProductRepository {

    override suspend fun getProductDetails(): Result<ProductDetails> {
        return withContext(ioDispatcher) {
            try {
                val dto = remoteDataSource.getProductDetails()
                val domain = dto.toEntity()
                localDataSource.saveProductDetails(domain.toCacheEntity())
                Result.Success(domain)
            } catch (e: Exception) {
                val cached = localDataSource.getProductDetails()
                if (cached != null) {
                    Result.Success(cached.toDomain())
                } else {
                    Result.Error(e)
                }
            }
        }
    }
}

