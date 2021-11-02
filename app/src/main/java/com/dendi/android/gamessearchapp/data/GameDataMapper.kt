package com.dendi.android.gamessearchapp.data

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GameDataMapper : Abstract.Mapper {
    fun map(
        id: Int,
        thumbnail: String,
        title: String,
    ): GameData

    class Base : GameDataMapper {
        override fun map(
            id: Int,
            thumbnail: String,
            title: String,
        ) = GameData(
            id = id,
            thumbnail = thumbnail,
            title = title
        )
    }
}