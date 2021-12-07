package com.dendi.android.gamessearchapp.data.detail.cache

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailCacheMapper<T> : Abstract.Mapper {
    fun map(
        description: String,
        developer: String,
        freetogameProfileUrl: String,
        gameUrl: String,
        genre: String,
        id: Int,
        systemRequirements: SystemRequirementsCache,
        platform: String,
        publisher: String,
        releaseDate: String,
        screenshots: List<ScreenshotCache>,
        shortDescription: String,
        status: String,
        thumbnail: String,
        title: String,
    ): T

    class Base : DetailCacheMapper<DetailCache> {
        override fun map(
            description: String,
            developer: String,
            freetogameProfileUrl: String,
            gameUrl: String,
            genre: String,
            id: Int,
            systemRequirements: SystemRequirementsCache,
            platform: String,
            publisher: String,
            releaseDate: String,
            screenshots: List<ScreenshotCache>,
            shortDescription: String,
            status: String,
            thumbnail: String,
            title: String,
        ) = DetailCache(
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