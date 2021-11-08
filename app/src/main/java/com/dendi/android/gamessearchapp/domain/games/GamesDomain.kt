package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.GameData
import com.dendi.android.gamessearchapp.presentation.games.GamesUi
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesDomain : Abstract.Object.Ui.GamesObject {
    data class Success(
        private val games: List<GameData>,
        private val domainMapper: Abstract.GameMapper<GameDomain>,
    ) : GamesDomain() {
        override fun map(mapper: Abstract.GamesUiMapper<GamesUi>): GamesUi {
            return mapper.map(games.map { it.map(domainMapper) })
        }
    }

    data class Fail(private val exception: Exception) : GamesDomain() {
        override fun map(mapper: Abstract.GamesUiMapper<GamesUi>) = mapper.map(
            when (exception) {
                is UnknownHostException -> ErrorType.NO_CONNECTION
                is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                else -> ErrorType.GENERIC_ERROR
            }
        )
    }
}
