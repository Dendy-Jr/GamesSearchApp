package com.dendi.android.gamessearchapp.data.games.cache


import androidx.lifecycle.LiveData
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.Read
import com.dendi.android.gamessearchapp.core.Save
import com.dendi.android.gamessearchapp.data.games.GameData

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCacheDataSource : Save<List<GameData>>, Read<List<GameCache>> {

    fun searchGame(searchQuery: String): LiveData<List<GameCache.Base>>

    class Base(
        private val gameDao: GameDao,
        private val mapper: Abstract.ToGameMapper<GameCache.Base>,
    ) : GamesCacheDataSource {

        override fun searchGame(searchQuery: String) =
            gameDao.searchGame(searchQuery)

        override suspend fun save(data: List<GameData>) =
            gameDao.saveGamesToDb(data.map { it.map(mapper) })

        override suspend fun read() = gameDao.fetchAllGames()
    }
}