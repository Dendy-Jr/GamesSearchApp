package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailUiMapper<T> : Abstract.Mapper {
    fun map(
        description: String,
        developer: String,
        freetogameProfileUrl: String,
        gameUrl: String,
        genre: String,
        id: Int,
        systemRequirements: SystemRequirementsUi.Base,
        platform: String,
        publisher: String,
        releaseDate: String,
        screenshots: List<ScreenshotUi.Base>,
        shortDescription: String,
        status: String,
        thumbnail: String,
        title: String,
    ): T

    fun map(message: String): T
}