package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ListMapper

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */

data class ScreenshotsUi(private val screenshots: List<ScreenshotUi>) :
    Abstract.Mapper.ScreenshotsUiObject {
    override fun map(mapper: ListMapper<ScreenshotUi>) = mapper.map(screenshots)
}
