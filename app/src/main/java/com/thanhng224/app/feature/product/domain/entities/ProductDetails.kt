package com.thanhng224.app.feature.product.domain.entities

data class ProductDetails(
    val id: Int,
    val title: String,
    val description: String,
    val brand: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val availabilityStatus: String,
    val thumbnail: String,
    val images: List<String>,
    val tags: List<String>,
    val sku: String,
    val weight: Int,
    val warrantyInformation: String,
    val shippingInformation: String,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val dimensions: ProductDimensions,
    val reviews: List<ProductReview>,
    val meta: ProductMeta
)

data class ProductDimensions(
    val width: Double,
    val height: Double,
    val depth: Double
)

data class ProductReview(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)

data class ProductMeta(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String
)

