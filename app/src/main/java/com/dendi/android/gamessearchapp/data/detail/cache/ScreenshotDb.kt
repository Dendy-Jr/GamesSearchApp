package com.dendi.android.gamessearchapp.data.detail.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.ScreenshotData

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
@Entity(tableName = "screenshot_table")
data class ScreenshotDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "image")
    val image: String,
) : Abstract.Object.MapToData.ScreenshotObject {
    override fun map(mapper: Abstract.ScreenshotMapper<ScreenshotData>) = mapper.map(
        id = id, image = image
    )
}
