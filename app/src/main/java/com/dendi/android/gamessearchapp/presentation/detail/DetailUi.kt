package com.dendi.android.gamessearchapp.presentation.detail

import android.util.Log


/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */

interface DetailUi {

    fun <T> map(mapper: DetailUiMapper<T>): T =
        mapper.map(
            "",
            "",
            "",
            "",
            "",
            0,
            SystemRequirementsUi.Base.EMPTY,
            "",
            "",
            "",
            emptyList(),
            "",
            "",
            "",
            ""
        )

    object Progress : DetailUi {
        init {
            Log.d("TAG", "DetailUi Progress")
        }
    }

    data class Base(
        private val description: String,
        private val developer: String,
        private val freetogameProfileUrl: String,
        private val gameUrl: String,
        private val genre: String,
        private val id: Int,
        private val systemRequirements: SystemRequirementsUi.Base,
        private val platform: String,
        private val publisher: String,
        private val releaseDate: String,
        private val screenshots: List<ScreenshotUi.Base>,
        private val shortDescription: String,
        private val status: String,
        private val thumbnail: String,
        private val title: String,
    ) : DetailUi {
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
        init {
            Log.d("TAG", "DetailUi Base")
        }
    }

    data class Fail(private val message: String) : DetailUi {
        override fun <T> map(mapper: DetailUiMapper<T>) = mapper.map(message)
        init {
            Log.d("TAG", "DetailUi Fail")
        }
    }
}
