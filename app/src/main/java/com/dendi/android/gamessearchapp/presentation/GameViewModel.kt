package com.dendi.android.gamessearchapp.presentation

import androidx.lifecycle.*
import com.dendi.android.gamessearchapp.domain.GameDomainToUiMapper
import com.dendi.android.gamessearchapp.domain.GamesDomainToUiMapper
import com.dendi.android.gamessearchapp.domain.GamesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GameViewModel(
    private val gamesInteractor: GamesInteractor,
    private val domainsMapper: GamesDomainToUiMapper,
    private val domainMapper: GameDomainToUiMapper,
    private val communication: GamesCommunication,
) : ViewModel() {

    fun fetchGames() {
        communication.map(listOf(GameUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = gamesInteractor.fetchGames()
            val resultUi = resultDomain.map(domainsMapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun searchGame(searchQuery: String): LiveData<List<GameUi>> {
        return gamesInteractor.searchGame(searchQuery).map { games ->
            games.map { game ->
                game.map(domainMapper)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<GameUi>>) {
        communication.observe(owner, observer)
    }
}