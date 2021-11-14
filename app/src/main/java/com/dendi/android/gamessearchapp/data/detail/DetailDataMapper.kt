package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailDataMapper<T> : Abstract.Mapper {
    fun map(
        description: String,
        developer: String,
        freetogameProfileUrl: String,
        gameUrl: String,
        genre: String,
        id: Int,
        systemRequirements: SystemRequirementsData.Base,
        platform: String,
        publisher: String,
        releaseDate: String,
        screenshots: List<ScreenshotData.Base>,
        shortDescription: String,
        status: String,
        thumbnail: String,
        title: String,
    ): T

    class Base : DetailDataMapper<DetailData.Base> {
        override fun map(
            description: String,
            developer: String,
            freetogameProfileUrl: String,
            gameUrl: String,
            genre: String,
            id: Int,
            systemRequirements: SystemRequirementsData.Base,
            platform: String,
            publisher: String,
            releaseDate: String,
            screenshots: List<ScreenshotData.Base>,
            shortDescription: String,
            status: String,
            thumbnail: String,
            title: String,
        ) = DetailData.Base(
            description = description,
            developer = developer,
            freetogameProfileUrl = freetogameProfileUrl,
            gameUrl = gameUrl,
            genre = genre,
            id = id,
            systemRequirements = systemRequirements,
            platform = platform,
            publisher = publisher,
            releaseDate = releaseDate,
            screenshots = screenshots,
            shortDescription = shortDescription,
            status = status,
            thumbnail = thumbnail,
            title = title
        )
    }
}