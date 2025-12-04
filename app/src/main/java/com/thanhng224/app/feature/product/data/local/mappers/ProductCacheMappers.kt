package com.thanhng224.app.feature.product.data.local.mappers

import com.thanhng224.app.feature.product.data.local.entities.CachedDimensions
import com.thanhng224.app.feature.product.data.local.entities.CachedMeta
import com.thanhng224.app.feature.product.data.local.entities.CachedReview
import com.thanhng224.app.feature.product.data.local.entities.ProductDetailsEntity
import com.thanhng224.app.feature.product.domain.entities.ProductDetails
import com.thanhng224.app.feature.product.domain.entities.ProductDimensions
import com.thanhng224.app.feature.product.domain.entities.ProductMeta
import com.thanhng224.app.feature.product.domain.entities.ProductReview

fun ProductDetails.toCacheEntity(): ProductDetailsEntity {
    return ProductDetailsEntity(
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
        dimensions = dimensions.toCached(),
        reviews = reviews.map { it.toCached() },
        meta = meta.toCached()
    )
}

fun ProductDetailsEntity.toDomain(): ProductDetails {
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
        dimensions = dimensions.toDomain(),
        reviews = reviews.map { it.toDomain() },
        meta = meta.toDomain()
    )
}

private fun ProductDimensions.toCached(): CachedDimensions {
    return CachedDimensions(
        width = width,
        height = height,
        depth = depth
    )
}

private fun CachedDimensions.toDomain(): ProductDimensions {
    return ProductDimensions(
        width = width,
        height = height,
        depth = depth
    )
}

private fun ProductReview.toCached(): CachedReview {
    return CachedReview(
        rating = rating,
        comment = comment,
        date = date,
        reviewerName = reviewerName,
        reviewerEmail = reviewerEmail
    )
}

private fun CachedReview.toDomain(): ProductReview {
    return ProductReview(
        rating = rating,
        comment = comment,
        date = date,
        reviewerName = reviewerName,
        reviewerEmail = reviewerEmail
    )
}

private fun ProductMeta.toCached(): CachedMeta {
    return CachedMeta(
        createdAt = createdAt,
        updatedAt = updatedAt,
        barcode = barcode,
        qrCode = qrCode
    )
}

private fun CachedMeta.toDomain(): ProductMeta {
    return ProductMeta(
        createdAt = createdAt,
        updatedAt = updatedAt,
        barcode = barcode,
        qrCode = qrCode
    )
}
