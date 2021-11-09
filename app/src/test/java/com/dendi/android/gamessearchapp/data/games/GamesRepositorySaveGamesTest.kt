package com.dendi.android.gamessearchapp.data.games

import androidx.lifecycle.LiveData
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.cache.GameDb
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

        val actualCloud = repository.fetchGames()
        val expectedCloud = GamesData.Success(
            listOf(
                GameData(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                GameData(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                GameData(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
            )
        )

        Assert.assertEquals(expectedCloud, actualCloud)

        val actualCache = repository.fetchGames()
        val expectedCache = GamesData.Success(
            listOf(
                GameData(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                GameData(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                GameData(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
            )
        )

        Assert.assertEquals(actualCache, expectedCache)
    }

    private inner class TestGamesCloudDataSource : GamesCloudDataSource {
        override suspend fun fetchGames(): List<GameCloud> {
            return listOf(
                GameCloud(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                GameCloud(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                GameCloud(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
            )
        }
    }

    private inner class TestGamesCacheDataSource : GamesCacheDataSource {

        private val list = ArrayList<GameDb>()

        override suspend fun fetchGames() = list

        override suspend fun saveGames(games: List<GameData>) {
            games.map { game ->
                list.add(game.map(object : Abstract.GameMapper<GameDb> {
                    override fun map(id: Int, thumbnail: String, title: String) = GameDb(
                        id, thumbnail, title
                    )
                }))
            }
        }

        override fun searchGame(searchQuery: String): LiveData<List<GameDb>> {
            TODO("Not yet implemented")
            // not used here
        }
    }

}