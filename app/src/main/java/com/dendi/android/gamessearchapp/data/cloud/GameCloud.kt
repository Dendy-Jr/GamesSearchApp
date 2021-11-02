package com.dendi.android.gamessearchapp.data.cloud

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.GameData
import com.dendi.android.gamessearchapp.data.GameDataMapper
import com.google.gson.annotations.SerializedName

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
data class GameCloud(
    @SerializedName("id")
    private val id: Int,
    private val thumbnail: String,
    @SerializedName("title")
    private val title: String,
) : Abstract.Object<GameData, GameDataMapper> {
    override fun map(mapper: GameDataMapper) = mapper.map(
        id = id,
        thumbnail = thumbnail,
        title = title,
    )
}