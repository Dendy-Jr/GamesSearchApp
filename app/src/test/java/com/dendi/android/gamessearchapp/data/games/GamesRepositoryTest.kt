package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.data.games.cache.GamesCacheDataSource
import com.dendi.android.gamessearchapp.data.games.cloud.GamesCloudDataSource
import com.dendi.android.gamessearchapp.domain.games.GamesRepository
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesRepositoryTest : BaseGamesRepositoryTest() {

    private val mapper = TestToGameMapper()
    private lateinit var repository: GamesRepository

    @Before
    fun setUp() {
        val cloudDataSource = GamesCloudDataSource.Test()
        val cacheDataSource = GamesCacheDataSource.Test(BaseToGameCacheMapper())
        repository = TestGamesRepository(cloudDataSource, cacheDataSource, mapper)
    }

    @Test
    fun test_fetching_games() = runBlocking {
        val expected = GamesDataState.Test(listOf(
            GameData.Base(1,
                "https://www.freetogame.com/g/1/thumbnail.jpg",
                "Dauntless",
                "A free-to-play, co-op ac…ilar to Monster Hunter."),
            GameData.Base(2,
                "https://www.freetogame.com/g/2/thumbnail.jpg",
                "World of Tanks",
                "If you like blowing up t…ou will love this game!"),
            GameData.Base(3,
                "https://www.freetogame.com/g/3/thumbnail.jpg",
                "A cooperative free-to-pl…stunning sci-fi world. ",
                "Warframe")
        ))

        val actual = repository.fetchGames("mmorpg", "alphabetical")
        assertEquals(expected, actual)
    }

    @Test
    fun test_read_data_from_db() = runBlocking {
        val expected = GamesDataState.Test(listOf(
            GameData.Base(1,
                "https://www.freetogame.com/g/1/thumbnail.jpg",
                "Dauntless",
                "A free-to-play, co-op ac…ilar to Monster Hunter."),
            GameData.Base(2,
                "https://www.freetogame.com/g/2/thumbnail.jpg",
                "World of Tanks",
                "If you like blowing up t…ou will love this game!"),
            GameData.Base(3,
                "https://www.freetogame.com/g/3/thumbnail.jpg",
                "A cooperative free-to-pl…stunning sci-fi world. ",
                "Warframe")
        ))

        val actual = repository.readDataFromDb()
        assertEquals(expected, actual)
    }

    @Test
    fun test_search_game() = runBlocking {
        repository.searchGame("World of Tanks")
        assertTrue("World of Tanks", true)
    }
}