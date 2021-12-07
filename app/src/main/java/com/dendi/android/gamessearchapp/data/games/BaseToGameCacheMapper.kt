package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.cache.GameCache

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseToGameCacheMapper : Abstract.GameMapper<GameCache> {
    override fun map(
        id: Int,
        thumbnail: String,
        title: String,
        shortDescription: String,
    ) =
        GameCache(
            id = id, thumbnail = thumbnail, title = title, shortDescription = shortDescription
        )
}