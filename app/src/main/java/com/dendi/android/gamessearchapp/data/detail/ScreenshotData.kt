package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ScreenshotData : Abstract.DataObject {

    fun <T> map(mapper: Abstract.ScreenshotMapper<T>): T
    fun <T : Abstract.CacheObject> map(mapper: Abstract.ScreenshotMapper<T>): T

    data class Base(
        private val id: Int,
        private val image: String,
    ) : ScreenshotData {
        override fun <T> map(mapper: Abstract.ScreenshotMapper<T>) =
            mapper.map(id = id, image = image)

        override fun <T : Abstract.CacheObject> map(mapper: Abstract.ScreenshotMapper<T>) =
            mapper.map(id = id, image = image)
    }
}

