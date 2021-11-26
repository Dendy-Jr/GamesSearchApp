package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseToGameDataMapper : Abstract.ToGameMapper<GameData> {
    override fun map(
        id: Int,
        thumbnail: String,
        title: String,
        shortDescription: String,
    ) =
        GameData.Base(
            id = id, thumbnail = thumbnail, title = title, shortDescription = shortDescription
        )
}