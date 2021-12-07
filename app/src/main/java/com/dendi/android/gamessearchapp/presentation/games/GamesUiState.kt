package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ListMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */

data class GamesUiState(private val games: List<GameUi>) : Abstract.Mapper.GamesUiStateObject {
    override fun map(mapper: ListMapper<GameUi>) = mapper.map(games)
}
