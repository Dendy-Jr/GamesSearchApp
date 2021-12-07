package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ErrorType

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesDomainState : Abstract.Mapper.GamesDomainStateObject {

    override fun <T> map(mapper: GamesDomainStateToUiStateMapper<T>) = mapper.map(listOf())

    data class Success(
        private val games: List<GameDomain>,
    ) : GamesDomainState() {
        override fun <T> map(mapper: GamesDomainStateToUiStateMapper<T>) = mapper.map(games)
    }

    data class Fail(private val errorType: ErrorType) : GamesDomainState() {
        override fun <T> map(mapper: GamesDomainStateToUiStateMapper<T>) = mapper.map(errorType)
    }
}
