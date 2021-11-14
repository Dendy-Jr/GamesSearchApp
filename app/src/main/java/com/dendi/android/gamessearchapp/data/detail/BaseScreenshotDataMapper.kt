package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseScreenshotDataMapper : Abstract.ScreenshotMapper<ScreenshotData.Base> {
    override fun map(id: Int, image: String) = ScreenshotData.Base(id = id, image = image)
}