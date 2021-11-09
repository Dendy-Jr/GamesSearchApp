package com.dendi.android.gamessearchapp.data.detail.cloud

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.ScreenshotData
import com.google.gson.annotations.SerializedName

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
data class ScreenshotCloud(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
) : Abstract.Object.MapToData.ScreenshotObject {
    override fun map(mapper: Abstract.ScreenshotMapper<ScreenshotData>) = mapper.map(
        id = id ?: 0,
        image = image ?: ""
    )
}
