package com.thanhng224.app.feature.product.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thanhng224.app.feature.product.data.local.entities.ProductDetailsEntity

@Dao
interface ProductDetailsDao {
    @Query("SELECT * FROM product_details LIMIT 1")
    suspend fun getProductDetails(): ProductDetailsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: ProductDetailsEntity)
}
