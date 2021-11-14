package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.ListMapper

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class ScreenshotsUi {

    abstract fun map(mapper: ListMapper<ScreenshotUi>)

    class Base(private val screenshots: List<ScreenshotUi>) : ScreenshotsUi() {
        override fun map(mapper: ListMapper<ScreenshotUi>) = mapper.map(screenshots)
    }
}
