package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class UiGameMapper : Abstract.GameMapper<GameUi> {
    override fun map(id: Int, thumbnail: String, title: String) = GameUi.Base(
        id = id,
        thumbnail = thumbnail,
        title = title)
}