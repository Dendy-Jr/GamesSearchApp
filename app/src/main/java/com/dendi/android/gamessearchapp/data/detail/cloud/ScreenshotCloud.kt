package com.dendi.android.gamessearchapp.data.detail.cloud

import com.dendi.android.gamessearchapp.core.Abstract
import com.google.gson.annotations.SerializedName

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ScreenshotCloud : Abstract.CloudObject {

    fun <T> map(mapper: Abstract.ScreenshotMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val id: Int?,
        @SerializedName("image")
        private val image: String?,
    ) : ScreenshotCloud {
        override fun <T> map(mapper: Abstract.ScreenshotMapper<T>) =
            mapper.map(id = id ?: 0, image = image ?: "")
    }
}


