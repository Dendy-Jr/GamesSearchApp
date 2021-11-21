package com.dendi.android.gamessearchapp.presentation.games

import androidx.lifecycle.*
import com.dendi.android.gamessearchapp.domain.games.GamesDomainStateToUiMapper
import com.dendi.android.gamessearchapp.domain.games.GamesInteractor
import com.dendi.android.gamessearchapp.presentation.core.BaseViewModel
import com.dendi.android.gamessearchapp.presentation.games.filter.DataStoreFilter
import com.dendi.android.gamessearchapp.presentation.games.filter.GenreType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesViewModel(
    private val interactor: GamesInteractor,
    private val mapper: GamesDomainStateToUiMapper<List<GameUi>>,
    communication: GamesCommunication,
    private val dataStoreFilter: DataStoreFilter
) : BaseViewModel<GamesCommunication, List<GameUi>>(communication) {

    fun fetchGames() {
        communication.map(listOf(GameUi.Progress))
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.read()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun saveGenreType(type: GenreType) = viewModelScope.launch(Dispatchers.IO) {
        dataStoreFilter.saveGenreType(type)
    }

    fun readGenreType() = dataStoreFilter.readGenreType

    fun searchGame(searchQuery: String): LiveData<List<GameUi>> {
        return interactor.searchGame(searchQuery).map { games ->
            mapper.map(games)
        }
    }

    override fun saveScrollPosition(position: Int) = interactor.saveScrollPosition(position)
    override fun scrollPosition() = interactor.scrollPosition()
}