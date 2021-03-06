package com.dendi.android.gamessearchapp.data.games.cloud

import com.dendi.android.gamessearchapp.core.Abstract
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
    @SerializedName("short_description")
    private val shortDescription: String,
) : Abstract.Mapper.GamesObject {
    override fun <T> map(mapper: Abstract.GameMapper<T>) =
        mapper.map(
            id,
            thumbnail = thumbnail,
            title = title,
            shortDescription = shortDescription
        )
}
