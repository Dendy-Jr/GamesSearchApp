package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.ListMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesUiState {

    abstract fun map(mapper: ListMapper<GameUi>)

    data class Base(private val games: List<GameUi>) : GamesUiState() {
        override fun map(mapper: ListMapper<GameUi>) = mapper.map(games)

    }
}