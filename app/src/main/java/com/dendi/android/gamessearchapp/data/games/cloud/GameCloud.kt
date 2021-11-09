package com.dendi.android.gamessearchapp.data.games.cloud

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.GameData
import com.google.gson.annotations.SerializedName

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
data class GameCloud(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("thumbnail")
    private val thumbnail: String,
    @SerializedName("title")
    private val title: String,
) : Abstract.Object.MapToData.GameObject {
    override fun map(mapper: Abstract.GameMapper<GameData>) = mapper.map(
        id = id, thumbnail = thumbnail, title = title
    )
}