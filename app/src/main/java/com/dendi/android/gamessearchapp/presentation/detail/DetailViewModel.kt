package com.dendi.android.gamessearchapp.presentation.detail


import androidx.lifecycle.viewModelScope
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.detail.DetailDomainStateToUiMapper
import com.dendi.android.gamessearchapp.domain.detail.DetailInteractor
import com.dendi.android.gamessearchapp.domain.favorites.FavoriteDomain
import com.dendi.android.gamessearchapp.presentation.core.BaseViewModel
import com.dendi.android.gamessearchapp.presentation.favorites.FavoriteUi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailViewModel(
    private val detailInteractor: DetailInteractor,
    communication: DetailCommunication,
    private val mapper: DetailDomainStateToUiMapper<DetailUiState>,
    private val favoriteMapper: Abstract.FavoriteMapper<FavoriteDomain.Base>,
) : BaseViewModel<DetailCommunication, DetailUi>(communication) {

    fun saveToFavorite(game: FavoriteUi.Base) =
        viewModelScope.launch(Dispatchers.IO) {
            detailInteractor.saveToFavorite(game.map(favoriteMapper))
        }

    fun deleteFromFavorite(game: FavoriteUi.Base) =
        viewModelScope.launch(Dispatchers.IO) {
            detailInteractor.deleteFromFavorite(game.map(favoriteMapper))
        }

    fun fetchDetail(id: Int) {
        communication.map(DetailUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = detailInteractor.readId(id)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(communication)
            }
        }
    }
}