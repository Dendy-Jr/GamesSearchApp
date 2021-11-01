package com.dendi.android.gamessearchapp.domain

import com.dendi.android.gamessearchapp.data.GamesDataToDomainMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesInteractor {

    suspend fun fetchGames(): GamesDomain

    class Base(
        private val gamesRepository: GamesRepository,
        private val mapper: GamesDataToDomainMapper,
    ) : GamesInteractor {
        override suspend fun fetchGames(): GamesDomain {
            return gamesRepository.fetchGames().map(mapper)

        }
    }
}