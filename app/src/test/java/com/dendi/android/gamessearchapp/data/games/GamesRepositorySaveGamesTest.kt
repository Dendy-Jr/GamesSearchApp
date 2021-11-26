package com.dendi.android.gamessearchapp.data.games

import androidx.lifecycle.LiveData
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.cache.GameCache
import com.dendi.android.gamessearchapp.data.games.cache.GamesCacheDataSource
import com.dendi.android.gamessearchapp.data.games.cloud.GameCloud
import com.dendi.android.gamessearchapp.data.games.cloud.GamesCloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesRepositorySaveGamesTest : BaseGamesRepositoryTest() {

    @Test
    fun test_save_games() = runBlocking {
        val testCloudDataSource = TestGamesCloudDataSource()
        val testCacheDataSource = TestGamesCacheDataSource()
        val repository = BaseGamesRepository(
            testCloudDataSource,
            testCacheDataSource,
            TestToGameMapper()
        )

        val actualCloud = repository.read()
        val expectedCloud = GamesDataState.Success(
            listOf(
                GameData.Base(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                GameData.Base(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                GameData.Base(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
            )
        )

        Assert.assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.read()
        val expectedCache = GamesDataState.Success(
            listOf(
                GameData.Base(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                GameData.Base(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                GameData.Base(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
            )
        )

        Assert.assertEquals(actualCache, expectedCache)
    }

    private inner class TestGamesCloudDataSource : GamesCloudDataSource {
        override suspend fun read(): List<GameCloud> {
            return listOf(
                GameCloud.Base(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                GameCloud.Base(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                GameCloud.Base(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
            )
        }
    }

    private inner class TestGamesCacheDataSource : GamesCacheDataSource {

        private val list = ArrayList<GameCache>()

        override suspend fun read() = list

        override suspend fun save(games: List<GameData>) {
            games.map { game ->
                list.add(game.map(object : Abstract.ToGameMapper<GameCache> {
                    override fun map(id: Int, thumbnail: String, title: String) = GameCache.Base(
                        id, thumbnail, title
                    )
                }))
            }
        }

        override fun searchGame(searchQuery: String): LiveData<List<GameCache.Base>> {
            TODO("Not yet implemented")
            // not used here
        }
    }

}