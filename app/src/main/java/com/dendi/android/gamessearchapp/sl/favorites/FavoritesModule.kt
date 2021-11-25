package com.dendi.android.gamessearchapp.sl.favorites

import com.dendi.android.gamessearchapp.data.favorites.BaseFavoritesRepository
import com.dendi.android.gamessearchapp.data.favorites.FavoritesCacheDataSource
import com.dendi.android.gamessearchapp.domain.favorites.BaseFavoriteDomainMapper
import com.dendi.android.gamessearchapp.domain.favorites.FavoritesInteractor
import com.dendi.android.gamessearchapp.presentation.favorites.BaseFavoriteUiMapper
import com.dendi.android.gamessearchapp.presentation.favorites.FavoritesCommunication
import com.dendi.android.gamessearchapp.presentation.favorites.FavoritesViewModel
import com.dendi.android.gamessearchapp.sl.core.BaseModule
import com.dendi.android.gamessearchapp.sl.core.CoreModule

/**
 * @author Dendy-Jr on 15.11.2021
 * olehvynnytskyi@gmail.com
 */
class FavoritesModule(
    private val coreModule: CoreModule
) : BaseModule<FavoritesViewModel> {

    override fun viewModel(): FavoritesViewModel {

        val favoritesInteractor = FavoritesInteractor.Base(
            BaseFavoritesRepository(
                FavoritesCacheDataSource.Base(
                    coreModule.favoriteDao
                )
            ),
            coreModule.scrollPositionCache,
            BaseFavoriteDomainMapper()
        )

        val communication = FavoritesCommunication.Base()

        return FavoritesViewModel(
            favoritesInteractor,
            communication,
            BaseFavoriteUiMapper(),
            coreModule.resourceProvider
        )
    }
}