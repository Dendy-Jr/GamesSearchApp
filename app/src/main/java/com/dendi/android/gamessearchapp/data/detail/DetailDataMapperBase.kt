package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailDataMapperBase : Abstract.DetailDataMapper<DetailData> {
    override fun map(
        description: String,
        developer: String,
        freetogameProfileUrl: String,
        gameUrl: String,
        genre: String,
        id: Int,
        systemRequirements: SystemRequirementsData?,
        platform: String,
        publisher: String,
        releaseDate: String,
        screenshots: List<ScreenshotData>?,
        shortDescription: String,
        status: String,
        thumbnail: String,
        title: String,
    ) = DetailData(
        description = description,
        developer = developer,
        freetogameProfileUrl = freetogameProfileUrl,
        gameUrl = gameUrl,
        genre = genre,
        id = id,
        systemRequirements = systemRequirements ?: SystemRequirementsData.EMPTY ,
        platform = platform,
        publisher = publisher,
        releaseDate = releaseDate,
        screenshots = screenshots ?: emptyList(),
        shortDescription = shortDescription,
        status = status,
        thumbnail = thumbnail,
        title = title
    )
}