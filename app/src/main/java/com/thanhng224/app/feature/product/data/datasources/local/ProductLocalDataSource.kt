package com.thanhng224.app.feature.product.data.datasources.local

import com.thanhng224.app.feature.product.data.local.ProductDetailsDao
import com.thanhng224.app.feature.product.data.local.entities.ProductDetailsEntity
import javax.inject.Inject

class ProductLocalDataSource @Inject constructor(
    private val productDetailsDao: ProductDetailsDao
) {

    suspend fun getProductDetails(): ProductDetailsEntity? {
        return productDetailsDao.getProductDetails()
    }

    suspend fun saveProductDetails(entity: ProductDetailsEntity) {
        productDetailsDao.upsert(entity)
    }
}
