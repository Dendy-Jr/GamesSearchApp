package com.dendi.android.gamessearchapp.presentation

import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.ErrorType
import com.dendi.android.gamessearchapp.domain.GameDomain
import com.dendi.android.gamessearchapp.domain.GameDomainToUiMapper
import com.dendi.android.gamessearchapp.domain.GamesDomainToUiMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGamesDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val gameMapper: GameDomainToUiMapper,
) : GamesDomainToUiMapper {
    override fun map(games: List<GameDomain>): GamesUi {
        return GamesUi.Success(games, gameMapper)
    }

    override fun map(errorType: ErrorType): GamesUi {
        return GamesUi.Fail(errorType, resourceProvider)
    }
}