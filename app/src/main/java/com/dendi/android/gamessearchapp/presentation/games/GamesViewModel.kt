package com.dendi.android.gamessearchapp.presentation.games

import androidx.lifecycle.*
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.games.GamesInteractor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesViewModel(
    private val gamesInteractor: GamesInteractor,
    private val gamesMapper: Abstract.GamesDomainToUiMapper<GamesUi>,
    private val gameMapper: Abstract.GameMapper<GameUi>,
    private val gamesCommunication: GamesCommunication,
) : ViewModel() {

    init {
        fetchGames()
    }

    fun fetchGames() {
        gamesCommunication.map(listOf(GameUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = gamesInteractor.fetchGames()
            val resultUi = resultDomain.map(gamesMapper)
            withContext(Dispatchers.Main) {
                resultUi.map(gamesCommunication)
            }
        }
    }

    fun searchGame(searchQuery: String): LiveData<List<GameUi>> {
        return gamesInteractor.searchGame(searchQuery).map { games ->
            games.map { game ->
                game.map(gameMapper)
            }
        }
    }

    fun observeGames(owner: LifecycleOwner, observer: Observer<List<GameUi>>) {
        gamesCommunication.observe(owner, observer)
    }
}