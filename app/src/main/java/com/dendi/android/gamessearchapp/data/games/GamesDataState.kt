package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.games.GamesDataStateToDomainMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GamesDataState : Abstract.DataObject {

    abstract fun <T> map(mapper: GamesDataStateToDomainMapper<T>): T

    data class Success(private val games: List<GameData>) : GamesDataState() {
        override fun <T> map(mapper: GamesDataStateToDomainMapper<T>) = mapper.map(games)
    }

    data class Fail(private val exception: Exception) : GamesDataState() {
        override fun <T> map(mapper: GamesDataStateToDomainMapper<T>) = mapper.map(exception)
    }
}