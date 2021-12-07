package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */

data class ScreenshotUi(
    private val id: Int,
    private val image: String,
) : Abstract.Mapper.ScreenshotObject {
    override fun <T> map(mapper: Abstract.ScreenshotMapper<T>) =
        mapper.map(id = id, image = image)
}



