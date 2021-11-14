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
    override suspend fun read() = try {
        val cacheList = cacheDataSource.read()
        if (cacheList.isEmpty()) {
            val responseData = cloudDataSource.read()
            val games = responseData.map {
                it.map(mapper)
            }
            cacheDataSource.save(games)
            GamesDataState.Success(games)
        } else {
            GamesDataState.Success(cacheList.map { it.map(mapper) })
        }
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