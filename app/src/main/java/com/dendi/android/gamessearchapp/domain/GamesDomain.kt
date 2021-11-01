package com.dendi.android.gamessearchapp.domain

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.GameData
import com.dendi.android.gamessearchapp.data.GameDataToDomainMapper
import com.dendi.android.gamessearchapp.presentation.GamesUi
import retrofit2.HttpException
import java.net.UnknownHostException

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesDomain : Abstract.Object<GamesUi, GamesDomainToUiMapper> {
    data class Success(
        private val games: List<GameData>,
        private val gameMapper: GameDataToDomainMapper,
    ) : GamesDomain() {
        override fun map(mapper: GamesDomainToUiMapper): GamesUi {
            return mapper.map(games.map {
                it.map(gameMapper)
            })
        }
    }

    data class Fail(private val e: Exception) : GamesDomain() {
        override fun map(mapper: GamesDomainToUiMapper): GamesUi {
            return mapper.map(
                when (e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                    else -> ErrorType.GENERIC_ERROR
                }
            )
        }
    }

}