package com.dendi.android.gamessearchapp.data.games.cache


import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.Save
import com.dendi.android.gamessearchapp.core.Show
import com.dendi.android.gamessearchapp.data.games.GameData

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCacheDataSource : Save<List<GameData>>, Show<List<GameCache>> {

    fun searchGame(searchQuery: String): LiveData<List<GameCache.Base>>

    class Base(
        private val gameDao: GameDao,
        private val mapper: Abstract.ToGameMapper<GameCache.Base>,
    ) : GamesCacheDataSource {

        override fun searchGame(searchQuery: String) =
            gameDao.searchGame(searchQuery)

        override suspend fun save(data: List<GameData>) =
            gameDao.saveGamesToDb(data.map { it.map(mapper) })

        override suspend fun show() = gameDao.fetchAllGames()
    }

    class Test(private val mapper: Abstract.ToGameMapper<GameCache.Base>) : GamesCacheDataSource {

        private val gamesList = mutableListOf<GameCache>()

        val games = listOf(
            GameCache.Base(1,
                "https://www.freetogame.com/g/1/thumbnail.jpg",
                "Dauntless",
                "A free-to-play, co-op ac…ilar to Monster Hunter."),
            GameCache.Base(2,
                "https://www.freetogame.com/g/2/thumbnail.jpg",
                "World of Tanks",
                "If you like blowing up t…ou will love this game!"),
            GameCache.Base(3,
                "https://www.freetogame.com/g/3/thumbnail.jpg",
                "A cooperative free-to-pl…stunning sci-fi world. ",
                "Warframe")
        )

        override suspend fun save(data: List<GameData>) {
            gamesList.addAll(data.map { it.map(mapper) })
        }

        override suspend fun show(): List<GameCache> {

            gamesList.addAll(games)
            return gamesList
        }

        override fun searchGame(searchQuery: String): LiveData<List<GameCache.Base>> {
            return liveData { gamesList }
        }
    }
}