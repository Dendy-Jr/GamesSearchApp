package com.dendi.android.gamessearchapp.domain.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.Read
import com.dendi.android.gamessearchapp.data.games.GamesScrollPositionCache
import com.dendi.android.gamessearchapp.presentation.core.ScrollPosition

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesInteractor : ScrollPosition, Read<GamesDomainState> {

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

        override suspend fun read() = gamesRepository.read().map(gamesDomainMapper)

        override fun searchGame(searchQuery: String) =
            gamesRepository.searchGame(searchQuery).map { games ->
                games.map { game ->
                    game.map(gameDomainMapper)
                }
            }
    }
}