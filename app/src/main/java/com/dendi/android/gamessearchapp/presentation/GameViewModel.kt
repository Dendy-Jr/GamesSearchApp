package com.dendi.android.gamessearchapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val mapper: GamesDomainToUiMapper,
    private val communication: GamesCommunication,
) : ViewModel() {

    fun fetchGames() {
        communication.map(listOf(GameUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = gamesInteractor.fetchGames()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<List<GameUi>>) {
        communication.observe(owner, observer)
    }
}