package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.games.GamesUi

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesDomain : Abstract.Object.MapToUi.GamesObject {
    data class Success(
        private val games: List<GameDomain>,
    ) : GamesDomain() {
        override fun map(mapper: Abstract.GamesDomainToUiMapper<GamesUi>) =
            mapper.map(games)
    }

    data class Fail(private val errorType: ErrorType) : GamesDomain() {
        override fun map(mapper: Abstract.GamesDomainToUiMapper<GamesUi>) = mapper.map(errorType)
    }
}
