package com.thanhng224.app.feature.product.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "product_details")
data class ProductDetailsEntity(
    @PrimaryKey val id: Int,
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
    val dimensions: CachedDimensions,
    val reviews: List<CachedReview>,
    val meta: CachedMeta
)

@Serializable
data class CachedDimensions(
    val width: Double,
    val height: Double,
    val depth: Double
)

@Serializable
data class CachedReview(
    val rating: Int,
    val comment: String,
    val date: String,
    val reviewerName: String,
    val reviewerEmail: String
)

@Serializable
data class CachedMeta(
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String
)
