package com.dendi.android.gamessearchapp.data.detail.cache

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseScreenshotCacheMapper : Abstract.ScreenshotMapper<ScreenshotCache> {
    override fun map(id: Int, image: String) = ScreenshotCache(id = id, image = image)
}