package com.dendi.android.gamessearchapp.presentation.games

import androidx.lifecycle.*
import com.dendi.android.gamessearchapp.domain.games.GamesDomainStateToUiMapper
import com.dendi.android.gamessearchapp.domain.games.GamesInteractor
import com.dendi.android.gamessearchapp.presentation.core.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesViewModel(
    private val gamesInteractor: GamesInteractor,
    private val gamesMapper: GamesDomainStateToUiMapper<GamesUiState>,
    private val gamesCommunication: GamesCommunication,
) : BaseViewModel<GamesCommunication, GamesUiState>(gamesCommunication) {

    init {
        fetchGames()
    }

    fun fetchGames() {
        gamesCommunication.map(GamesUiState.Base(listOf(GameUi.Progress)))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = gamesInteractor.read()
            val resultUi = resultDomain.map(gamesMapper)
            withContext(Dispatchers.Main) {
                gamesCommunication.map(resultUi)
            }
        }
    }

    override fun saveScrollPosition(position: Int) = gamesInteractor.saveScrollPosition(position)

    override fun scrollPosition() = gamesInteractor.scrollPosition()

    fun searchGame(searchQuery: String): LiveData<GamesUiState> {
        return gamesInteractor.searchGame(searchQuery).map { games ->
            gamesMapper.map(games)
        }
    }
}