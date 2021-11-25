package com.dendi.android.gamessearchapp.domain.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.GamesScrollPositionCache
import com.dendi.android.gamessearchapp.presentation.core.ScrollPosition

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesInteractor : ScrollPosition {

    suspend fun fetchGames(category: String, sort: String): GamesDomainState

    suspend fun readDataFromDb(): GamesDomainState

    fun searchGame(searchQuery: String): LiveData<List<GameDomain>>

    class Base(
        private val gamesRepository: GamesRepository,
        private val gamesDomainMapper: GamesDataStateToDomainMapper<GamesDomainState>,
        private val gameDomainMapper: Abstract.ToGameMapper<GameDomain>,
        private val scrollPosition: GamesScrollPositionCache,
    ) : GamesInteractor {

        override fun saveScrollPosition(position: Int) =
            scrollPosition.saveGamesScrollPosition(position)

        override fun scrollPosition() = scrollPosition.gamesScrollPosition()

        override suspend fun fetchGames(category: String, sort: String) =
            gamesRepository.fetchGames(category, sort).map(gamesDomainMapper)

        override fun searchGame(searchQuery: String) =
            gamesRepository.searchGame(searchQuery).map { games ->
                games.map { game ->
                    game.map(gameDomainMapper)
                }
            }

        override suspend fun readDataFromDb() =
            gamesRepository.readDataFromDb().map(gamesDomainMapper)
    }
}