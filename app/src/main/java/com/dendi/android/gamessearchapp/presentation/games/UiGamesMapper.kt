package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.games.ErrorType
import com.dendi.android.gamessearchapp.domain.games.GameDomain

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class UiGamesMapper(
    private val uiMapper: Abstract.GameMapper<GameUi>,
    private val resourceProvider: ResourceProvider,
) : Abstract.GamesUiMapper<GamesUi> {
    override fun map(games: List<GameDomain>) = GamesUi.Success(games, uiMapper)
    override fun map(errorType: ErrorType) = GamesUi.Fail(errorType, resourceProvider)
}