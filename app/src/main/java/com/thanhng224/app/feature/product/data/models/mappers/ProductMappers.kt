package com.thanhng224.app.feature.product.data.models.mappers

import com.thanhng224.app.feature.product.data.models.DimensionsDto
import com.thanhng224.app.feature.product.data.models.MetaDto
import com.thanhng224.app.feature.product.data.models.ProductDetailsDto
import com.thanhng224.app.feature.product.data.models.ReviewDto
import com.thanhng224.app.feature.product.domain.entities.ProductDetails
import com.thanhng224.app.feature.product.domain.entities.ProductDimensions
import com.thanhng224.app.feature.product.domain.entities.ProductMeta
import com.thanhng224.app.feature.product.domain.entities.ProductReview

fun ProductDetailsDto.toEntity(): ProductDetails {
    return ProductDetails(
        id = id,
        title = title,
        description = description,
        brand = brand,
        category = category,
        price = price,
        discountPercentage = discountPercentage,
        rating = rating,
        stock = stock,
        availabilityStatus = availabilityStatus,
        thumbnail = thumbnail,
        images = images,
        tags = tags,
        sku = sku,
        weight = weight,
        warrantyInformation = warrantyInformation,
        shippingInformation = shippingInformation,
        returnPolicy = returnPolicy,
        minimumOrderQuantity = minimumOrderQuantity,
        dimensions = dimensions.toEntity(),
        reviews = reviews.map { it.toEntity() },
        meta = meta.toEntity()
    )
}

fun DimensionsDto.toEntity(): ProductDimensions {
    return ProductDimensions(
        width = width,
        height = height,
        depth = depth
    )
}

fun ReviewDto.toEntity(): ProductReview {
    return ProductReview(
        rating = rating,
        comment = comment,
        date = date,
        reviewerName = reviewerName,
        reviewerEmail = reviewerEmail
    )
}

fun MetaDto.toEntity(): ProductMeta {
    return ProductMeta(
        createdAt = createdAt,
        updatedAt = updatedAt,
        barcode = barcode,
        qrCode = qrCode
    )
}

