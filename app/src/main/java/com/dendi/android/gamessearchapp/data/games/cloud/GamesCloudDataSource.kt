package com.dendi.android.gamessearchapp.data.games.cloud


/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCloudDataSource {

    suspend fun fetchGames(category: String, sort: String): List<GameCloud>

    class Base(private val service: GameService) : GamesCloudDataSource {
        override suspend fun fetchGames(category: String, sort: String) =
            service.fetchGames(category = category, sort = sort).body()!!
    }

    class Test : GamesCloudDataSource {
        override suspend fun fetchGames(category: String, sort: String): List<GameCloud> {
            return listOf(
                GameCloud(1,
                    "https://www.freetogame.com/g/1/thumbnail.jpg",
                    "Dauntless",
                    "A free-to-play, co-op ac…ilar to Monster Hunter."),
                GameCloud(2,
                    "https://www.freetogame.com/g/2/thumbnail.jpg",
                    "World of Tanks",
                    "If you like blowing up t…ou will love this game!"),
                GameCloud(3,
                    "https://www.freetogame.com/g/3/thumbnail.jpg",
                    "A cooperative free-to-pl…stunning sci-fi world. ",
                    "Warframe")
            )
        }
    }
}