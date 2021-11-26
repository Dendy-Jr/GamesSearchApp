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
}