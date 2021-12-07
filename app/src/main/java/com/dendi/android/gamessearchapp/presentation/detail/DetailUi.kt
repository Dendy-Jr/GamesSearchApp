package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.Abstract


/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */

sealed class DetailUi : Abstract.Mapper.DetailUiObject {

    override fun <T> map(mapper: DetailUiMapper<T>) =
        mapper.map(
            "",
            "",
            "",
            "",
            "",
            0,
            SystemRequirementsUi.EMPTY,
            "",
            "",
            "",
            emptyList(),
            "",
            "",
            "",
            ""
        )


    object Progress : DetailUi()

    data class Base(
        private val description: String,
        private val developer: String,
        private val freetogameProfileUrl: String,
        private val gameUrl: String,
        private val genre: String,
        private val id: Int,
        private val systemRequirements: SystemRequirementsUi,
        private val platform: String,
        private val publisher: String,
        private val releaseDate: String,
        private val screenshots: List<ScreenshotUi>,
        private val shortDescription: String,
        private val status: String,
        private val thumbnail: String,
        private val title: String,
    ) : DetailUi() {
        override fun <T> map(mapper: DetailUiMapper<T>) = mapper.map(
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

    data class Fail(private val message: String) : DetailUi() {
        override fun <T> map(mapper: DetailUiMapper<T>) = mapper.map(message)

    }
}
