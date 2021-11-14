package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.ErrorType

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesDomainState {

    abstract fun <T> map(mapper: GamesDomainStateToUiMapper<T>): T

    data class Success(
        private val games: List<GameDomain>,
    ) : GamesDomainState() {
        override fun <T> map(mapper: GamesDomainStateToUiMapper<T>) = mapper.map(games)
    }

    data class Fail(private val errorType: ErrorType) : GamesDomainState() {
        override fun <T> map(mapper: GamesDomainStateToUiMapper<T>) = mapper.map(errorType)
    }
}
