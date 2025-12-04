package com.thanhng224.app.core.di

import android.content.Context
import androidx.room.Room
import com.thanhng224.app.feature.product.data.local.ProductDatabase
import com.thanhng224.app.feature.product.data.local.ProductDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideProductDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
                context,
                ProductDatabase::class.java,
                "product-db"
            ).fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideProductDetailsDao(database: ProductDatabase): ProductDetailsDao {
        return database.productDetailsDao()
    }
}
