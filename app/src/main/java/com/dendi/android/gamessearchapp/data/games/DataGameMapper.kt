package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class DataGameMapper : Abstract.GameMapper<GameData> {
    override fun map(id: Int, thumbnail: String, title: String) = GameData(
        id = id, thumbnail = thumbnail, title = title
    )
}