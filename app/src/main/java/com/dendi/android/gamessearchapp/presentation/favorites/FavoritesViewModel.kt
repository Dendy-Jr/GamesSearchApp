package com.dendi.android.gamessearchapp.presentation.favorites

import androidx.lifecycle.viewModelScope
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.domain.favorites.FavoritesInteractor
import com.dendi.android.gamessearchapp.presentation.core.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
class FavoritesViewModel(
    private val interactor: FavoritesInteractor,
    communication: FavoritesCommunication,
    private val mapper: Abstract.FavoriteMapper<FavoriteUi>,
    resourceProvider: ResourceProvider
) : BaseViewModel<FavoritesCommunication, List<FavoriteUi>>(communication, resourceProvider) {

    fun fetchFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = interactor.show()
            withContext(Dispatchers.Main) {
                communication.map(resultDomain.map { it.map(mapper) })
            }
        }
    }

    override fun saveScrollPosition(position: Int) = interactor.saveScrollPosition(position)

    override fun scrollPosition() = interactor.scrollPosition()
}