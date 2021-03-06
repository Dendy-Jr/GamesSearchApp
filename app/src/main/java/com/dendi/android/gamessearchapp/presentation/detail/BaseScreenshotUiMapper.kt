package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseScreenshotUiMapper : Abstract.ScreenshotMapper<ScreenshotUi> {
    override fun map(id: Int, image: String) = ScreenshotUi(id = id, image = image)
}