package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.cache.DetailDb
import com.dendi.android.gamessearchapp.domain.detail.DetailDomain
import com.dendi.android.gamessearchapp.domain.detail.DomainScreenshotMapper
import com.dendi.android.gamessearchapp.domain.detail.DomainSystemRequirementsMapper


/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
data class DetailData(
    private val description: String,
    private val developer: String,
    private val freetogameProfileUrl: String,
    private val gameUrl: String,
    private val genre: String,
    private val id: Int,
    private val systemRequirements: SystemRequirementsData,
    private val platform: String,
    private val publisher: String,
    private val releaseDate: String,
    private val screenshots: List<ScreenshotData>,
    private val shortDescription: String,
    private val status: String,
    private val thumbnail: String,
    private val title: String,
) : Abstract.Object.Domain.DetailObject,
    DbObject<DetailData, DetailDb> {
    override fun map(mapper: Abstract.DetailDomainMapper<DetailDomain>) = mapper.map(
        description = description,
        developer = developer,
        freetogameProfileUrl = freetogameProfileUrl,
        gameUrl = gameUrl,
        genre = genre,
        id = id,
        systemRequirements = systemRequirements.map(DomainSystemRequirementsMapper()),
        platform = platform,
        publisher = publisher,
        releaseDate = releaseDate,
        screenshots = screenshots.map { it.map(DomainScreenshotMapper()) },
        shortDescription = shortDescription,
        status = status,
        thumbnail = thumbnail,
        title = title
    )

    override fun map(data: DetailData): DetailDb {
        return DetailDb(
            description = description,
            developer = developer,
            freetogameProfileUrl = freetogameProfileUrl,
            gameUrl = gameUrl,
            genre = genre,
            id = id,
            systemRequirements = systemRequirements.map(DbSystemRequirementsMapper()),
            platform = platform,
            publisher = publisher,
            releaseDate = releaseDate,
            screenshots = screenshots.map { it.map(DbScreenshotMapper()) },
            shortDescription = shortDescription,
            status = status,
            thumbnail = thumbnail,
            title = title
        )
    }
}

interface DbObject<S, R> {
    fun map(data: S): R
}


