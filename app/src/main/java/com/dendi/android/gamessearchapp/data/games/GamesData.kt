package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.games.GamesDomain

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesData : Abstract.Object.Domain.GamesObject {
    data class Success(private val games: List<GameData>) : GamesData() {
        override fun map(mapper: Abstract.GamesDomainMapper<GamesDomain>) = mapper.map(games)
    }

    data class Fail(private val exception: Exception) : GamesData() {
        override fun map(mapper: Abstract.GamesDomainMapper<GamesDomain>) = mapper.map(exception)
    }
}