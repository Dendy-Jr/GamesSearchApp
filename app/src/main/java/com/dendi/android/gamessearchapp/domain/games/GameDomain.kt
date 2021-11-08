package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.games.GameUi


/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
data class GameDomain(
    private val id: Int,
    private val thumbnail: String,
    private val title: String,
) : Abstract.Object.Ui.GameObject {
    override fun map(mapper: Abstract.GameMapper<GameUi>) = mapper.map(
        id = id, thumbnail = thumbnail, title = title
    )
}