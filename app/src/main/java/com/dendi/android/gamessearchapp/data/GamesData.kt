package com.dendi.android.gamessearchapp.data

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.GamesDomain

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesData : Abstract.Object<GamesDomain, GamesDataToDomainMapper> {
    data class Success(private val games: List<GameData>) : GamesData() {
        override fun map(mapper: GamesDataToDomainMapper): GamesDomain {
            return mapper.map(games)
        }
    }

    data class Fail(private val e: Exception) : GamesData() {
        override fun map(mapper: GamesDataToDomainMapper): GamesDomain {
            return mapper.map(e)
        }
    }
}