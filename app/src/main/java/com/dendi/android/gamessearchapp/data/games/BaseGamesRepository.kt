package com.dendi.android.gamessearchapp.data.games

import androidx.lifecycle.map
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.cache.GamesCacheDataSource
import com.dendi.android.gamessearchapp.data.games.cloud.GamesCloudDataSource
import com.dendi.android.gamessearchapp.domain.games.GamesRepository

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGamesRepository(
    private val gamesCloudDataSource: GamesCloudDataSource,
    private val gamesCacheDataSource: GamesCacheDataSource,
    private val mapper: Abstract.GameMapper<GameData>,
) : GamesRepository {
    override suspend fun fetchGames() = try {
        val cacheList = gamesCacheDataSource.fetchGames()
        if (cacheList.isEmpty()) {
            val responseData = gamesCloudDataSource.fetchGames()
            val games = responseData.map {
                it.map(mapper)
            }
            gamesCacheDataSource.saveGames(games)
            GamesData.Success(games)
        } else {
            GamesData.Success(cacheList.map { it.map(mapper) })
        }
    } catch (e: Exception) {
        GamesData.Fail(e)
    }

    override fun searchGame(searchQuery: String) =
        gamesCacheDataSource.searchGame(searchQuery).map { games ->
            games.map { game ->
                game.map(mapper)
            }
        }
}