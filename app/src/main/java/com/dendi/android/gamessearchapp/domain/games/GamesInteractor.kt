package com.dendi.android.gamessearchapp.domain.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesInteractor {

    suspend fun fetchGames(): GamesDomain

    fun searchGame(searchQuery: String): LiveData<List<GameDomain>>

    class Base(
        private val gamesRepository: GamesRepository,
        private val gamesDomainMapper: Abstract.GamesDataToDomainMapper<GamesDomain>,
        private val gameDomainMapper: Abstract.GameMapper<GameDomain>,
    ) : GamesInteractor {
        override suspend fun fetchGames() = gamesRepository.fetchGames().map(gamesDomainMapper)

        override fun searchGame(searchQuery: String) =
            gamesRepository.searchGame(searchQuery).map { games ->
                games.map { game ->
                    game.map(gameDomainMapper)
                }
            }
    }
}