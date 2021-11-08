package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.detail.ScreenshotUi


/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
data class ScreenshotDomain(
    private val id: Int,
    private val image: String,
) : Abstract.Object.Ui.ScreenshotObject {
    override fun map(mapper: Abstract.ScreenshotMapper<ScreenshotUi>) = mapper.map(
        id = id, image = image
    )
}
