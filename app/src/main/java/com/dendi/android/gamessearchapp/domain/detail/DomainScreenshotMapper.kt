package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class DomainScreenshotMapper : Abstract.ScreenshotMapper<ScreenshotDomain> {
    override fun map(id: Int, image: String) = ScreenshotDomain(
        id = id,
        image = image
    )
}