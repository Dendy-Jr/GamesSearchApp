package com.dendi.android.gamessearchapp.data.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.cache.GameCache
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
    private val cacheMapper: Abstract.GameMapper<GameCache>,
    private val dataMapper: Abstract.GameMapper<GameData>,
) : GamesRepository {

    override suspend fun readDataFromDb() =
        GamesDataState.Success(cacheDataSource.show().map { it.map(dataMapper) })

    override suspend fun fetchGames(category: String, sort: String): GamesDataState = try {
        val responseData = cloudDataSource.fetchGames(category, sort)
        val games = responseData.map {
            it.map(cacheMapper)
        }
        cacheDataSource.save(games)
        GamesDataState.Success(games.map { it.map(dataMapper) })
    } catch (e: Exception) {
        GamesDataState.Fail(e)
    }

    override fun searchGame(searchQuery: String) =
        cacheDataSource.searchGame(searchQuery).map { games ->
            games.map { it.map(dataMapper) }
        }
}

class TestGamesRepository(
    private val cloudDataSource: GamesCloudDataSource.Test,
    private val cacheCloudDataSource: GamesCacheDataSource.Test,
    private val cacheMapper: Abstract.GameMapper<GameCache>,
    private val dataMapper: Abstract.GameMapper<GameData>,
) : GamesRepository {

    override suspend fun fetchGames(category: String, sort: String): GamesDataState {
        return GamesDataState.Test(cloudDataSource.fetchGames(category, sort).map {
            it.map(cacheMapper).map(dataMapper)
        })
    }

    override suspend fun readDataFromDb(): GamesDataState {
        return GamesDataState.Test(cacheCloudDataSource.show().map { it.map(dataMapper) })
    }

    override fun searchGame(searchQuery: String): LiveData<List<GameData>> {
        return cacheCloudDataSource.searchGame(searchQuery).map {
            it.map { it.map(dataMapper) }
        }
    }
}