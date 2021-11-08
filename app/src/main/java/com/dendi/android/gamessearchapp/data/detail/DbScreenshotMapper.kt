package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.cache.ScreenshotDb

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class DbScreenshotMapper : Abstract.ScreenshotMapper<ScreenshotDb> {
    override fun map(id: Int, image: String) = ScreenshotDb(id = id, image = image)
}