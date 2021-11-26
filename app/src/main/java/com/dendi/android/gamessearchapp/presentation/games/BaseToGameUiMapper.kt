package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseToGameUiMapper : Abstract.ToGameMapper<GameUi> {
    override fun map(
        id: Int,
        thumbnail: String,
        title: String,
        shortDescription: String,
    ) =
        GameUi.Base(
            id = id,
            thumbnail = thumbnail,
            title = title,
            shortDescription = shortDescription
        )
}