package com.dendi.android.gamessearchapp.data

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.cache.GameDb

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GameDataToDbMapper : Abstract.Mapper {
    fun map(
        id: Int,
        thumbnail: String,
        title: String,
    ): GameDb

    class Base : GameDataToDbMapper {
        override fun map(
            id: Int,
            thumbnail: String,
            title: String,
        ): GameDb {
            return GameDb(
                id,
                thumbnail,
                title
            )
        }
    }
}