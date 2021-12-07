package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseToGameDomainMapper : Abstract.GameMapper<GameDomain> {
    override fun map(
        id: Int,
        thumbnail: String,
        title: String,
        shortDescription: String,
    ) =
        GameDomain(
            id = id, thumbnail = thumbnail, title = title, shortDescription = shortDescription
        )
}