package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesUi : Abstract.Object.UnitObject<GamesCommunication> {
    data class Base(private val games: List<GameUi>) : GamesUi() {
        override fun map(mapper: GamesCommunication) = mapper.map(games)
    }
}