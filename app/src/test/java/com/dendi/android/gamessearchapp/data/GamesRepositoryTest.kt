package com.dendi.android.gamessearchapp.data

import com.dendi.android.gamessearchapp.data.cache.GameDb
import com.dendi.android.gamessearchapp.data.cache.GamesCacheDataSource
import com.dendi.android.gamessearchapp.data.cloud.GameCloud
import com.dendi.android.gamessearchapp.data.cloud.GamesCloudDataSource
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import java.net.UnknownHostException

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesRepositoryTest : BaseGamesRepositoryTest() {

    private val unknownHostException = UnknownHostException()

    @Test
    fun test_no_connection_cache() = runBlocking {
        val testCloudDataSource = TestGamesCloudDataSource(false)
        val testCacheDataSource = TestGamesCacheDataSource(false)
        val repository = BaseGamesRepository(
            testCloudDataSource,
            testCacheDataSource,
            TestToGameMapper()
        )

        val actual = repository.fetchGames()
        val expected = GamesData.Fail(unknownHostException)

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_no_cache() = runBlocking {
        val testCloudDataSource = TestGamesCloudDataSource(true)
        val testCacheDataSource = TestGamesCacheDataSource(false)
        val repository = BaseGamesRepository(
            testCloudDataSource,
            testCacheDataSource,
            TestToGameMapper())

        val actual = repository.fetchGames()
        val expected = GamesData.Success(
            listOf(
                GameData(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                GameData(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                GameData(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
            )
        )
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_no_connection_with_cache() = runBlocking {
        val testCloudDataSource = TestGamesCloudDataSource(false)
        val testCacheDataSource = TestGamesCacheDataSource(true)
        val repository = BaseGamesRepository(
            testCloudDataSource,
            testCacheDataSource,
            TestToGameMapper())

        val actual = repository.fetchGames()
        val expected = GamesData.Success(
            listOf(
                GameData(10, "https://www.freetogame.com/g/10/thumbnail.jpg", "ArcheAge"),
                GameData(11, "https://www.freetogame.com/g/11/thumbnail.jpg", "Neverwinter"),
                GameData(12, "https://www.freetogame.com/g/12/thumbnail.jpg", "War Thunder")
            )
        )

        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_cloud_success_with_cache() = runBlocking {
        val testCloudDataSource = TestGamesCloudDataSource(true)
        val testCacheDataSource = TestGamesCacheDataSource(true)
        val repository = BaseGamesRepository(
            testCloudDataSource,
            testCacheDataSource,
            TestToGameMapper())

        val actual = repository.fetchGames()
        val expected = GamesData.Success(
            listOf(
                GameData(10, "https://www.freetogame.com/g/10/thumbnail.jpg", "ArcheAge"),
                GameData(11, "https://www.freetogame.com/g/11/thumbnail.jpg", "Neverwinter"),
                GameData(12, "https://www.freetogame.com/g/12/thumbnail.jpg", "War Thunder")
            )
        )

        Assert.assertEquals(expected, actual)
    }

    private inner class TestGamesCloudDataSource(
        private val returnSuccess: Boolean,
    ) : GamesCloudDataSource {
        override suspend fun fetchGames(): List<GameCloud> {
            if (returnSuccess) {
                return listOf(
                    GameCloud(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                    GameCloud(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                    GameCloud(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
                )
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestGamesCacheDataSource(
        private val returnSuccess: Boolean,
    ) : GamesCacheDataSource {
        override suspend fun fetchGames(): List<GameDb> {
            return if (returnSuccess) {
                listOf(
                    GameDb(10, "https://www.freetogame.com/g/10/thumbnail.jpg", "ArcheAge"),
                    GameDb(11, "https://www.freetogame.com/g/11/thumbnail.jpg", "Neverwinter"),
                    GameDb(12, "https://www.freetogame.com/g/12/thumbnail.jpg", "War Thunder")
                )
            } else {
                emptyList()
            }

        }

        override suspend fun saveGames(games: List<GameData>) {
            // not used here
        }
    }
}