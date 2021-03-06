package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.detail.BaseScreenshotUiMapper
import com.dendi.android.gamessearchapp.presentation.detail.BaseSystemRequirementsUiMapper

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */

data class DetailDomain(
    private val description: String,
    private val developer: String,
    private val freetogameProfileUrl: String,
    private val gameUrl: String,
    private val genre: String,
    private val id: Int,
    private val systemRequirements: SystemRequirementsDomain,
    private val platform: String,
    private val publisher: String,
    private val releaseDate: String,
    private val screenshots: List<ScreenshotDomain>,
    private val shortDescription: String,
    private val status: String,
    private val thumbnail: String,
    private val title: String,
) : Abstract.Mapper.DetailDomainObject {
    override fun <T> map(mapper: DetailDomainToUiMapper<T>) =
        mapper.map(
            description = description,
            developer = developer,
            freetogameProfileUrl = freetogameProfileUrl,
            gameUrl = gameUrl,
            genre = genre,
            id = id,
            systemRequirements = systemRequirements.map(BaseSystemRequirementsUiMapper()),
            platform = platform,
            publisher = publisher,
            releaseDate = releaseDate,
            screenshots = screenshots.map { it.map(BaseScreenshotUiMapper()) },
            shortDescription = shortDescription,
            status = status,
            thumbnail = thumbnail,
            title = title
        )

    companion object {
        val EMPTY = DetailDomain(
            "",
            "",
            "",
            "",
            "",
            0,
            SystemRequirementsDomain(0, "", "", "", "", ""),
            "",
            "",
            "",
            listOf(),
            "",
            "",
            "",
            "")
    }
}
