package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.cache.GameDb
import com.dendi.android.gamessearchapp.domain.games.GameDomain

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
data class GameData(
    private val id: Int,
    private val thumbnail: String,
    private val title: String,
) : Abstract.Object.MapToCache.GameObject<GameDb> {
    override fun map(mapper: Abstract.GameMapper<GameDomain>) = mapper.map(
        id = id, thumbnail = thumbnail, title = title
    )

    override fun map(mapper: Abstract.GameMapper<GameDb>) = mapper.map(
        id = id, thumbnail = thumbnail, title = title
    )
}
