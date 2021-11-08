package com.dendi.android.gamessearchapp.data.games.cache


import androidx.lifecycle.LiveData
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.GameData

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCacheDataSource {

    suspend fun fetchGames(): List<GameDb>

    suspend fun saveGames(games: List<GameData>)

    fun searchGame(searchQuery: String): LiveData<List<GameDb>>

    class Base(
        private val gameDao: GameDao,
        private val mapper: Abstract.GameMapper<GameDb>,
    ) : GamesCacheDataSource {
        override suspend fun fetchGames() = gameDao.fetchAllGames()

        override suspend fun saveGames(games: List<GameData>) = gameDao.saveGamesToDb(
            games.map {
                it.map(mapper)
            })

        override fun searchGame(searchQuery: String) =
            gameDao.searchGame(searchQuery)
    }
}