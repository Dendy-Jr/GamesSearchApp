package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.domain.detail.DetailDomainToUiMapper

/**
 * @author Dendy-Jr on 05.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailDomainToUiMapperBase : DetailDomainToUiMapper<DetailUi> {
    override fun map(
        description: String,
        developer: String,
        freetogameProfileUrl: String,
        gameUrl: String,
        genre: String,
        id: Int,
        systemRequirements: SystemRequirementsUi,
        platform: String,
        publisher: String,
        releaseDate: String,
        screenshots: List<ScreenshotUi>,
        shortDescription: String,
        status: String,
        thumbnail: String,
        title: String,
    ) = DetailUi.Base(
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