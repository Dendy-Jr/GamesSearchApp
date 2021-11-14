package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract


/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ScreenshotDomain {

    fun <T> map(mapper: Abstract.ScreenshotMapper<T>): T

    data class Base(
        private val id: Int,
        private val image: String,
    ) : ScreenshotDomain {
        override fun <T> map(mapper: Abstract.ScreenshotMapper<T>) = mapper.map(
            id = id, image = image
        )
    }
}

