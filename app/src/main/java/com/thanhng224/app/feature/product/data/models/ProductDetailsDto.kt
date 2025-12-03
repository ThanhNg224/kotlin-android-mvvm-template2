package com.thanhng224.app.feature.product.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDetailsDto(
    @SerialName("availabilityStatus")
    val availabilityStatus: String,
    @SerialName("brand")
    val brand: String,
    @SerialName("category")
    val category: String,
    @SerialName("description")
    val description: String,
    @SerialName("dimensions")
    val dimensions: DimensionsDto,
    @SerialName("discountPercentage")
    val discountPercentage: Double,
    @SerialName("id")
    val id: Int,
    @SerialName("images")
    val images: List<String>,
    @SerialName("meta")
    val meta: MetaDto,
    @SerialName("minimumOrderQuantity")
    val minimumOrderQuantity: Int,
    @SerialName("price")
    val price: Double,
    @SerialName("rating")
    val rating: Double,
    @SerialName("returnPolicy")
    val returnPolicy: String,
    @SerialName("reviews")
    val reviews: List<ReviewDto>,
    @SerialName("shippingInformation")
    val shippingInformation: String,
    @SerialName("sku")
    val sku: String,
    @SerialName("stock")
    val stock: Int,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String,
    @SerialName("warrantyInformation")
    val warrantyInformation: String,
    @SerialName("weight")
    val weight: Int
)

@Serializable
data class DimensionsDto(
    @SerialName("depth")
    val depth: Double,
    @SerialName("height")
    val height: Double,
    @SerialName("width")
    val width: Double
)

@Serializable
data class ReviewDto(
    @SerialName("comment")
    val comment: String,
    @SerialName("date")
    val date: String,
    @SerialName("rating")
    val rating: Int,
    @SerialName("reviewerEmail")
    val reviewerEmail: String,
    @SerialName("reviewerName")
    val reviewerName: String
)

@Serializable
data class MetaDto(
    @SerialName("barcode")
    val barcode: String,
    @SerialName("createdAt")
    val createdAt: String,
    @SerialName("qrCode")
    val qrCode: String,
    @SerialName("updatedAt")
    val updatedAt: String
)

