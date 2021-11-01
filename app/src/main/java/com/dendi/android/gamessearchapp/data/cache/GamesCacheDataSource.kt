package com.dendi.android.gamessearchapp.data.cache


import com.dendi.android.gamessearchapp.data.GameData
import com.dendi.android.gamessearchapp.data.GameDataToDbMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCacheDataSource {

    suspend fun fetchGames(): List<GameDb>

    suspend fun saveGames(games: List<GameData>)

    class Base(
        private val gameDao: GameDao,
        private val mapper: GameDataToDbMapper,
    ) : GamesCacheDataSource {
        override suspend fun fetchGames(): List<GameDb> {
            return gameDao.fetchAllGames()
        }

        override suspend fun saveGames(games: List<GameData>) {
            return gameDao.saveGamesToDb(games.map {
                it.map(mapper)
            })
        }
    }
}