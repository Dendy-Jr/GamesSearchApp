package com.dendi.android.gamessearchapp.data.games

import androidx.lifecycle.LiveData
import com.dendi.android.gamessearchapp.data.games.cache.GameCache
import com.dendi.android.gamessearchapp.data.games.cache.GamesCacheDataSource
import com.dendi.android.gamessearchapp.data.games.cloud.GameCloud
import com.dendi.android.gamessearchapp.data.games.cloud.GamesCloudDataSource
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

        val actual = repository.read()
        val expected = GamesDataState.Fail(unknownHostException)

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

        val actual = repository.read()
        val expected = GamesDataState.Success(
            listOf(
                GameData.Base(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                GameData.Base(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                GameData.Base(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
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

        val actual = repository.read()
        val expected = GamesDataState.Success(
            listOf(
                GameData.Base(10, "https://www.freetogame.com/g/10/thumbnail.jpg", "ArcheAge"),
                GameData.Base(11, "https://www.freetogame.com/g/11/thumbnail.jpg", "Neverwinter"),
                GameData.Base(12, "https://www.freetogame.com/g/12/thumbnail.jpg", "War Thunder")
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

        val actual = repository.read()
        val expected = GamesDataState.Success(
            listOf(
                GameData.Base(10, "https://www.freetogame.com/g/10/thumbnail.jpg", "ArcheAge"),
                GameData.Base(11, "https://www.freetogame.com/g/11/thumbnail.jpg", "Neverwinter"),
                GameData.Base(12, "https://www.freetogame.com/g/12/thumbnail.jpg", "War Thunder")
            )
        )

        Assert.assertEquals(expected, actual)
    }

    private inner class TestGamesCloudDataSource(
        private val returnSuccess: Boolean,
    ) : GamesCloudDataSource {
        override suspend fun read(): List<GameCloud> {
            if (returnSuccess) {
                return listOf(
                    GameCloud.Base(0, "https://www.freetogame.com/g/1/thumbnail.jpg", "Dauntless"),
                    GameCloud.Base(1, "https://www.freetogame.com/g/2/thumbnail.jpg", "World of Tanks"),
                    GameCloud.Base(2, "https://www.freetogame.com/g/3/thumbnail.jpg", "Warframe")
                )
            } else {
                throw unknownHostException
            }
        }
    }

    private inner class TestGamesCacheDataSource(
        private val returnSuccess: Boolean,
    ) : GamesCacheDataSource {
        override suspend fun read(): List<GameCache> {
            return if (returnSuccess) {
                listOf(
                    GameCache.Base(10, "https://www.freetogame.com/g/10/thumbnail.jpg", "ArcheAge"),
                    GameCache.Base(11, "https://www.freetogame.com/g/11/thumbnail.jpg", "Neverwinter"),
                    GameCache.Base(12, "https://www.freetogame.com/g/12/thumbnail.jpg", "War Thunder")
                )
            } else {
                emptyList()
            }

        }

        override suspend fun save(data: List<GameData>) {
            // not used here
        }

        override fun searchGame(searchQuery: String): LiveData<List<GameCache.Base>> {
            TODO("Not yet implemented")
            // not used here
        }
    }
}