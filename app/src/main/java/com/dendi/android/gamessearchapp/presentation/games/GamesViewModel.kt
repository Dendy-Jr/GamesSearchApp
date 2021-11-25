package com.dendi.android.gamessearchapp.presentation.games

import androidx.lifecycle.*
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.games.GamesDomainStateToUiMapper
import com.dendi.android.gamessearchapp.domain.games.GamesInteractor
import com.dendi.android.gamessearchapp.presentation.core.BaseViewModel
import com.dendi.android.gamessearchapp.presentation.games.filter.DataStoreRepository
import com.dendi.android.gamessearchapp.presentation.games.filter.GamesCategory
import com.dendi.android.gamessearchapp.presentation.games.filter.GamesSort
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
    private val dataStoreFilter: DataStoreRepository,
    resourceProvider: ResourceProvider,
) : BaseViewModel<GamesCommunication, GamesUiState>(communication, resourceProvider) {

    fun fetchGames(category: String, sort: String) {
        if (checkForInternet()) {
            readDataFromNetwork(category = category, sort = sort)
        } else {
            readDataFromDb()
        }
    }


    private fun readDataFromNetwork(category: String, sort: String) {
        communication.map(GamesUiState.Base(listOf(GameUi.Progress)))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.fetchGames(category = category, sort)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    private fun readDataFromDb() {
        communication.map(GamesUiState.Base(listOf(GameUi.Progress)))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.readDataFromDb()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun saveGamesCategory(category: GamesCategory) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreFilter.saveGamesCategory(category)
        }

    fun saveGamesSort(sort: GamesSort) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreFilter.saveGamesSort(sort)
        }

    fun readGamesCategory() = dataStoreFilter.readGamesCategory

    fun readGamesSort() = dataStoreFilter.readGamesSort

    fun searchGame(searchQuery: String): LiveData<GamesUiState> {
        return interactor.searchGame(searchQuery).map { mapper.map(it) }
    }

    override fun saveScrollPosition(position: Int) = interactor.saveScrollPosition(position)
    override fun scrollPosition() = interactor.scrollPosition()
}