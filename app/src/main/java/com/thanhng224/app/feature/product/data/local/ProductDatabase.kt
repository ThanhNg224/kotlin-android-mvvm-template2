package com.thanhng224.app.feature.product.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.thanhng224.app.feature.product.data.local.entities.ProductDetailsEntity

@Database(
    entities = [ProductDetailsEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ProductDatabase : RoomDatabase() {
    abstract fun productDetailsDao(): ProductDetailsDao
}
