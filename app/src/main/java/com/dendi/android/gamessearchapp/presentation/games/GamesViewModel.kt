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
    private val interactor: GamesInteractor,
    private val mapper: GamesDomainStateToUiMapper<GamesUiState>,
    communication: GamesCommunication,
) : BaseViewModel<GamesCommunication, GamesUiState>(communication) {

    fun fetchGames() {
        communication.map(GamesUiState.Base(listOf(GameUi.Progress)))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.read()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun searchGame(searchQuery: String): LiveData<GamesUiState> {
        return interactor.searchGame(searchQuery).map { games ->
            mapper.map(games)
        }
    }

    override fun saveScrollPosition(position: Int) = interactor.saveScrollPosition(position)
    override fun scrollPosition() = interactor.scrollPosition()
}