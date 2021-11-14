package com.dendi.android.gamessearchapp.data.games.cloud

import com.dendi.android.gamessearchapp.core.Read

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCloudDataSource : Read<List<GameCloud>> {

    class Base(private val service: GameService) : GamesCloudDataSource {
        override suspend fun read() = service.fetchGames().body()!!
    }
}