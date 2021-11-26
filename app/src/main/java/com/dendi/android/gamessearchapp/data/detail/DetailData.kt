package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.cache.BaseScreenshotCacheMapper
import com.dendi.android.gamessearchapp.data.detail.cache.BaseSystemRequirementsCacheMapper
import com.dendi.android.gamessearchapp.data.detail.cache.DetailDataToCacheMapper
import com.dendi.android.gamessearchapp.data.detail.cache.SystemRequirementsCache
import com.dendi.android.gamessearchapp.domain.detail.BaseScreenshotDomainMapper
import com.dendi.android.gamessearchapp.domain.detail.BaseSystemRequirementsDomainMapper
import com.dendi.android.gamessearchapp.domain.detail.SystemRequirementsDomain


/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailData : Abstract.DataObject {

    fun <T> map(mapper: DetailDataToDomainMapper<T>): T
    fun <T> map(mapper: DetailDataToCacheMapper<T>): T

    data class Base(
        private val description: String,
        private val developer: String,
        private val freetogameProfileUrl: String,
        private val gameUrl: String,
        private val genre: String,
        private val id: Int,
        private val systemRequirements: SystemRequirementsData.Base,
        private val platform: String,
        private val publisher: String,
        private val releaseDate: String,
        private val screenshots: List<ScreenshotData.Base>,
        private val shortDescription: String,
        private val status: String,
        private val thumbnail: String,
        private val title: String,
    ) : DetailData {
        override fun <T> map(mapper: DetailDataToDomainMapper<T>) =
            mapper.map(description = description,
                developer = developer,
                freetogameProfileUrl = freetogameProfileUrl,
                gameUrl = gameUrl,
                genre = genre,
                id = id,
                systemRequirements = systemRequirements.map(BaseSystemRequirementsDomainMapper()) as SystemRequirementsDomain.Base,
                platform = platform,
                publisher = publisher,
                releaseDate = releaseDate,
                screenshots = screenshots.map { it.map(BaseScreenshotDomainMapper()) },
                shortDescription = shortDescription,
                status = status,
                thumbnail = thumbnail,
                title = title)

        override fun <T> map(mapper: DetailDataToCacheMapper<T>) =
            mapper.map(
                description = description,
                developer = developer,
                freetogameProfileUrl = freetogameProfileUrl,
                gameUrl = gameUrl,
                genre = genre,
                id = id,
                systemRequirements = systemRequirements.map(BaseSystemRequirementsCacheMapper()) as SystemRequirementsCache.Base,
                platform = platform,
                publisher = publisher,
                releaseDate = releaseDate,
                screenshots = screenshots.map { it.map(BaseScreenshotCacheMapper()) },
                shortDescription = shortDescription,
                status = status,
                thumbnail = thumbnail,
                title = title
            )

    }
}
