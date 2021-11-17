package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseToGameDomainMapper : Abstract.ToGameMapper<GameDomain> {
    override fun map(
        id: Int,
        thumbnail: String,
        title: String,
        genre: String,
        releaseDate: String
    ) =
        GameDomain.Base(
            id = id, thumbnail = thumbnail, title = title, genre = genre, releaseDate = releaseDate
        )
}