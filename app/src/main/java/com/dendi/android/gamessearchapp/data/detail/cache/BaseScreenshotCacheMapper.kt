package com.dendi.android.gamessearchapp.data.detail.cache

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseScreenshotCacheMapper : Abstract.ScreenshotMapper<ScreenshotCache.Base> {
    override fun map(id: Int, image: String) = ScreenshotCache.Base(id = id, image = image)
}