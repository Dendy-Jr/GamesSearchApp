package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.cache.GameCache

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseToGameCacheMapper : Abstract.ToGameMapper<GameCache.Base> {
    override fun map(
        id: Int,
        thumbnail: String,
        title: String,
        genre: String,
        releaseDate: String
    ) =
        GameCache.Base(
            id = id, thumbnail = thumbnail, title = title, genre = genre, releaseDate = releaseDate
        )
}