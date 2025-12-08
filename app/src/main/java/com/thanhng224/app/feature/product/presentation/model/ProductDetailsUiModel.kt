package com.thanhng224.app.feature.product.presentation.model

import com.thanhng224.app.feature.product.domain.entities.ProductDetails

data class ProductDetailsUiModel(
    val title: String,
    val brand: String,
    val description: String,
    val primaryImage: String?
)

fun ProductDetails.toUiModel(): ProductDetailsUiModel = ProductDetailsUiModel(
    title = title,
    brand = brand,
    description = description,
    primaryImage = images.firstOrNull()
)
