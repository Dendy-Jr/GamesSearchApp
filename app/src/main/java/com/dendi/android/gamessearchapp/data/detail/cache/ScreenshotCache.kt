package com.dendi.android.gamessearchapp.data.detail.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ScreenshotCache : Abstract.CacheObject {

    fun <T> map(mapper: Abstract.ScreenshotMapper<T>): T

    @Entity(tableName = "screenshot_table")
    data class Base(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Int,
        @ColumnInfo(name = "image")
        val image: String,
    ) : ScreenshotCache {
        override fun <T> map(mapper: Abstract.ScreenshotMapper<T>) =
            mapper.map(id = id, image = image)
    }
}

