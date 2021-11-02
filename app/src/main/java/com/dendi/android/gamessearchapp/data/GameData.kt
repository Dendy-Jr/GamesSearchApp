package com.dendi.android.gamessearchapp.data

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.cache.GameDb
import com.dendi.android.gamessearchapp.domain.GameDomain

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
data class GameData(
    private val id: Int,
    private val thumbnail: String,
    private val title: String,
) : Abstract.Object<GameDb, GameDataToDbMapper>,
    Data<GameDomain, GameDataToDomainMapper> {
    override fun map(mapper: GameDataToDbMapper) = mapper.map(
        id = id,
        thumbnail = thumbnail,
        title = title
    )

    override fun map(mapper: GameDataToDomainMapper) = GameDomain(
        id = id,
        thumbnail = thumbnail,
        title = title)
}

interface Data<T, M> : Abstract.Mapper {
    fun map(mapper: M): T
}