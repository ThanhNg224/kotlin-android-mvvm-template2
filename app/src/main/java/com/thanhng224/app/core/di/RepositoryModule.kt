package com.thanhng224.app.core.di

import com.thanhng224.app.feature.auth.data.repositories.AuthRepositoryImpl
import com.thanhng224.app.feature.auth.domain.repositories.AuthRepository
import com.thanhng224.app.feature.onboarding.data.repositories.OnboardingRepositoryImpl
import com.thanhng224.app.feature.onboarding.domain.repositories.OnboardingRepository
import com.thanhng224.app.feature.product.data.repositories.ProductRepositoryImpl
import com.thanhng224.app.feature.product.domain.repositories.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepository(
        productRepositoryImpl: ProductRepositoryImpl
    ): ProductRepository

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository
}

