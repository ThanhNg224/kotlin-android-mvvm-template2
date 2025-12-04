package com.thanhng224.app.feature.product.data.local

import androidx.room.TypeConverter
import com.thanhng224.app.feature.product.data.local.entities.CachedDimensions
import com.thanhng224.app.feature.product.data.local.entities.CachedMeta
import com.thanhng224.app.feature.product.data.local.entities.CachedReview
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object Converters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromStringList(list: List<String>?): String? = list?.let { json.encodeToString(it) }

    @TypeConverter
    fun toStringList(value: String?): List<String>? = value?.let { json.decodeFromString(it) }

    @TypeConverter
    fun fromReviews(list: List<CachedReview>?): String? = list?.let { json.encodeToString(it) }

    @TypeConverter
    fun toReviews(value: String?): List<CachedReview>? = value?.let { json.decodeFromString(it) }

    @TypeConverter
    fun fromDimensions(dimensions: CachedDimensions?): String? = dimensions?.let { json.encodeToString(it) }

    @TypeConverter
    fun toDimensions(value: String?): CachedDimensions? = value?.let { json.decodeFromString(it) }

    @TypeConverter
    fun fromMeta(meta: CachedMeta?): String? = meta?.let { json.encodeToString(it) }

    @TypeConverter
    fun toMeta(value: String?): CachedMeta? = value?.let { json.decodeFromString(it) }
}
