package com.dendi.android.gamessearchapp.data.cloud

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCloudDataSource {

    suspend fun fetchGames(): List<GameCloud>

    class Base(private val service: GameService) : GamesCloudDataSource {
        override suspend fun fetchGames() = service.fetchGames().body()!!
    }
}