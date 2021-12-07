package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.detail.BaseScreenshotDomainMapper
import com.dendi.android.gamessearchapp.domain.detail.BaseSystemRequirementsDomainMapper


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
) : Abstract.Mapper.DetailDataObject {

    override fun <T> map(mapper: DetailDataToDomainMapper<T>) =
        mapper.map(
            description = description,
            developer = developer,
            freetogameProfileUrl = freetogameProfileUrl,
            gameUrl = gameUrl,
            genre = genre,
            id = id,
            systemRequirements = systemRequirements.map(BaseSystemRequirementsDomainMapper()),
            platform = platform,
            publisher = publisher,
            releaseDate = releaseDate,
            screenshots = screenshots.map { it.map(BaseScreenshotDomainMapper()) },
            shortDescription = shortDescription,
            status = status,
            thumbnail = thumbnail,
            title = title
        )

    companion object {
        val EMPTY = DetailData(
            "",
            "",
            "",
            "",
            "",
            0,
            SystemRequirementsData(0, "", "", "", "", ""),
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
