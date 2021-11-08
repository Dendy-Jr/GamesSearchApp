package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class DataScreenshotMapper : Abstract.ScreenshotMapper<ScreenshotData> {
    override fun map(id: Int, image: String) = ScreenshotData(id = id, image = image)
}