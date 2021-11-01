package com.dendi.android.gamessearchapp.domain

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.GamesUi

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesDomainToUiMapper : Abstract.Mapper {
    fun map(games: List<GameDomain>): GamesUi
    fun map(errorType: ErrorType): GamesUi
}