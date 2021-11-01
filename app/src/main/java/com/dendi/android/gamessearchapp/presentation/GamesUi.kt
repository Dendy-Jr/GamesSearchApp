package com.dendi.android.gamessearchapp.presentation

import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.ErrorType
import com.dendi.android.gamessearchapp.domain.GameDomain
import com.dendi.android.gamessearchapp.domain.GameDomainToUiMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesUi : Abstract.Object<Unit, GamesCommunication> {

    class Success(
        private val games: List<GameDomain>,
        private val gameMapper: GameDomainToUiMapper,
    ) : GamesUi() {
        override fun map(mapper: GamesCommunication) {
            return mapper.map(games.map {
                it.map(gameMapper)
            })
        }
    }

    class Fail(
        private val errorType: ErrorType,
        private val resourceProvider: ResourceProvider,
    ) : GamesUi() {
        override fun map(mapper: GamesCommunication) {
            val messageId = when (errorType) {
                ErrorType.NO_CONNECTION -> R.string.no_connection_message
                ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                else -> R.string.something_went_wrong
            }
            val message = resourceProvider.getString(messageId)
            mapper.map(listOf(GameUi.Fail(message)))
        }
    }
}