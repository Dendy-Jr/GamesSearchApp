package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.R
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.games.ErrorType
import com.dendi.android.gamessearchapp.domain.games.GameDomain

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGamesDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val uiMapper: Abstract.GameMapper<GameUi>,
) : Abstract.GamesDomainToUiMapper<GamesUi> {
    override fun map(games: List<GameDomain>) = GamesUi.Base(games.map {
        it.map(uiMapper)
    })

    override fun map(errorType: ErrorType): GamesUi {
        val messageId = when (errorType) {
            ErrorType.NO_CONNECTION -> R.string.no_connection_message
            ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
            else -> R.string.something_went_wrong
        }
        val message = resourceProvider.getString(messageId)
        return GamesUi.Base(listOf(GameUi.Fail(message)))
    }
}