package com.dendi.android.gamessearchapp.data.detail.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.DataScreenshotMapper
import com.dendi.android.gamessearchapp.data.detail.DataSystemRequirementsMapper
import com.dendi.android.gamessearchapp.data.detail.DetailData

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
@Entity(tableName = "detail_table")
data class DetailDb(
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "developer")
    val developer: String,
    @ColumnInfo(name = "freetogame_profile_url")
    val freetogameProfileUrl: String,
    @ColumnInfo(name = "game_url")
    val gameUrl: String,
    @ColumnInfo(name = "genre")
    val genre: String,
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "minimum_system_requirements")
    val systemRequirements: SystemRequirementsDb,
    @ColumnInfo(name = "platform")
    val platform: String,
    @ColumnInfo(name = "publisher")
    val publisher: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "screenshots")
    val screenshots: List<ScreenshotDb>,
    @ColumnInfo(name = "short_description")
    val shortDescription: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "title")
    val title: String,
) : Abstract.Object.MapToData.DetailObject {
    override fun map(mapper: Abstract.DetailDataMapper<DetailData>) = mapper.map(
        description = description,
        developer = developer,
        freetogameProfileUrl = freetogameProfileUrl,
        gameUrl = gameUrl,
        genre = genre,
        id = id,
        systemRequirements = systemRequirements.map(DataSystemRequirementsMapper()),
        platform = platform,
        publisher = publisher,
        releaseDate = releaseDate,
        screenshots = screenshots.map { it.map(DataScreenshotMapper()) },
        shortDescription = shortDescription,
        status = status,
        thumbnail = thumbnail,
        title = title,
    )
}

