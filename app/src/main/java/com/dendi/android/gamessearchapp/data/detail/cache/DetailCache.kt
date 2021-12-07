package com.dendi.android.gamessearchapp.data.detail.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.DetailDataMapper
import com.dendi.android.gamessearchapp.data.detail.BaseScreenshotDataMapper
import com.dendi.android.gamessearchapp.data.detail.BaseSystemRequirementsDataMapper

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */

@Entity(tableName = "detail_table")
data class DetailCache(
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
    val systemRequirements: SystemRequirementsCache,
    @ColumnInfo(name = "platform")
    val platform: String,
    @ColumnInfo(name = "publisher")
    val publisher: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "screenshots")
    val screenshots: List<ScreenshotCache>,
    @ColumnInfo(name = "short_description")
    val shortDescription: String,
    @ColumnInfo(name = "status")
    val status: String,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "title")
    val title: String,
) : Abstract.Mapper.DetailCacheObject {
    override fun <T> map(mapper: DetailDataMapper<T>) =
        mapper.map(
            description = description,
            developer = developer,
            freetogameProfileUrl = freetogameProfileUrl,
            gameUrl = gameUrl,
            genre = genre,
            id = id,
            systemRequirements = systemRequirements.map(BaseSystemRequirementsDataMapper()),
            platform = platform,
            publisher = publisher,
            releaseDate = releaseDate,
            screenshots = screenshots.map { it.map(BaseScreenshotDataMapper()) },
            shortDescription = shortDescription,
            status = status,
            thumbnail = thumbnail,
            title = title,
        )

    companion object {
        val EMPTY = DetailCache(
            "",
            "",
            "",
            "",
            "",
            0,
            SystemRequirementsCache(0, "", "", "", "", ""),
            "",
            "",
            "",
            listOf(),
            "",
            "",
            "",
            ""
        )
    }
}



