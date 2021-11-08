package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.cache.GameDb

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class DbGameMapper : Abstract.GameMapper<GameDb> {
    override fun map(id: Int, thumbnail: String, title: String) = GameDb(
        id = id, thumbnail = thumbnail, title = title
    )
}