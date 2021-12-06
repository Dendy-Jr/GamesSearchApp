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

    data class Test(private val games: List<GameData>) : GamesDataState() {
        override fun <T> map(mapper: GamesDataStateToDomainMapper<T>) =
            mapper.map(listOf(
                GameData.Base(1,
                    "https://www.freetogame.com/g/1/thumbnail.jpg",
                    "Dauntless",
                    "A free-to-play, co-op ac…ilar to Monster Hunter."),
                GameData.Base(2,
                    "https://www.freetogame.com/g/2/thumbnail.jpg",
                    "World of Tanks",
                    "If you like blowing up t…ou will love this game!"),
                GameData.Base(3,
                    "https://www.freetogame.com/g/3/thumbnail.jpg",
                    "A cooperative free-to-pl…stunning sci-fi world. ",
                    "Warframe")
            ))
    }
}