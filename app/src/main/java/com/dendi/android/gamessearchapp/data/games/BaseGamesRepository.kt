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
    private val cloudDataSource: GamesCloudDataSource,
    private val cacheDataSource: GamesCacheDataSource,
    private val mapper: Abstract.ToGameMapper<GameData>,
) : GamesRepository {

    override suspend fun readDataFromDb() =
        GamesDataState.Success(cacheDataSource.show().map { it.map(mapper) })

    override suspend fun fetchGames(category: String, sort: String): GamesDataState = try {
        val responseData = cloudDataSource.fetchGames(category, sort)
        val games = responseData.map {
            it.map(mapper)
        }
        cacheDataSource.save(games)
        GamesDataState.Success(games)
    } catch (e: Exception) {
        GamesDataState.Fail(e)
    }

    override fun searchGame(searchQuery: String) =
        cacheDataSource.searchGame(searchQuery).map { games ->
            games.map { game ->
                game.map(mapper)
            }
        }
}