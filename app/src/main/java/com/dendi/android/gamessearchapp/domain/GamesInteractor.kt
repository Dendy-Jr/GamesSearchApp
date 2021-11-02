package com.dendi.android.gamessearchapp.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dendi.android.gamessearchapp.data.GameDataToDomainMapper
import com.dendi.android.gamessearchapp.data.GamesDataToDomainMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesInteractor {

    suspend fun fetchGames(): GamesDomain

    fun searchGame(searchQuery: String): LiveData<List<GameDomain>>

    class Base(
        private val gamesRepository: GamesRepository,
        private val domainsMapper: GamesDataToDomainMapper,
        private val domainMapper: GameDataToDomainMapper,
    ) : GamesInteractor {
        override suspend fun fetchGames() = gamesRepository.fetchGames().map(domainsMapper)

        override fun searchGame(searchQuery: String) =
            gamesRepository.searchGame(searchQuery).map { games ->
                games.map { game ->
                    game.map(domainMapper)
                }
            }
    }
}