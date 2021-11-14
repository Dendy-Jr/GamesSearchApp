package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.detail.ScreenshotUi
import com.dendi.android.gamessearchapp.presentation.detail.SystemRequirementsUi

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailDomainToUiMapper<T> : Abstract.Mapper {
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
}