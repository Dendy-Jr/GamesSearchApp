package com.dendi.android.gamessearchapp.data.detail.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */

@Entity(tableName = "screenshot_table")
data class ScreenshotCache(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "image")
    val image: String,
) : Abstract.Mapper.ScreenshotObject {
    override fun <T> map(mapper: Abstract.ScreenshotMapper<T>) =
        mapper.map(id = id, image = image)
}

