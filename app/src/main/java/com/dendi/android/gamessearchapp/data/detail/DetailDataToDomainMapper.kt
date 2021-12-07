package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.detail.DetailDomain
import com.dendi.android.gamessearchapp.domain.detail.ScreenshotDomain
import com.dendi.android.gamessearchapp.domain.detail.SystemRequirementsDomain

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        description: String,
        developer: String,
        freetogameProfileUrl: String,
        gameUrl: String,
        genre: String,
        id: Int,
        systemRequirements: SystemRequirementsDomain,
        platform: String,
        publisher: String,
        releaseDate: String,
        screenshots: List<ScreenshotDomain>,
        shortDescription: String,
        status: String,
        thumbnail: String,
        title: String,
    ): T

    class Base : DetailDataToDomainMapper<DetailDomain> {

        override fun map(
            description: String,
            developer: String,
            freetogameProfileUrl: String,
            gameUrl: String,
            genre: String,
            id: Int,
            systemRequirements: SystemRequirementsDomain,
            platform: String,
            publisher: String,
            releaseDate: String,
            screenshots: List<ScreenshotDomain>,
            shortDescription: String,
            status: String,
            thumbnail: String,
            title: String,
        ) = DetailDomain(
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