package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.cache.ScreenshotDb
import com.dendi.android.gamessearchapp.domain.detail.ScreenshotDomain

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
data class ScreenshotData(
    private val id: Int,
    private val image: String,
) : Abstract.Object.Cache.ScreenshotObject<ScreenshotDb> {
    override fun map(mapper: Abstract.ScreenshotMapper<ScreenshotDomain>) =
        mapper.map(id = id, image = image)

    override fun map(mapper: Abstract.ScreenshotMapper<ScreenshotDb>) = mapper.map(
        id = id, image = image)
}
