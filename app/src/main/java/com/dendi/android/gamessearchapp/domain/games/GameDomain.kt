package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract


/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */

data class GameDomain(
    private val id: Int,
    private val thumbnail: String,
    private val title: String,
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
