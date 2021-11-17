package com.dendi.android.gamessearchapp.domain.favorites

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.Read
import com.dendi.android.gamessearchapp.data.favorites.BaseFavoritesRepository
import com.dendi.android.gamessearchapp.data.favorites.FavoritesScrollPositionCache
import com.dendi.android.gamessearchapp.presentation.core.ScrollPosition

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoritesInteractor : ScrollPosition, Read<List<FavoriteDomain>> {

    class Base(
        private val favoritesRepository: BaseFavoritesRepository,
        private val scrollPosition: FavoritesScrollPositionCache,
        private val mapperDomain: Abstract.FavoriteMapper<FavoriteDomain.Base>,
    ) : FavoritesInteractor {

        override fun saveScrollPosition(position: Int) =
            scrollPosition.saveFavoritesScrollPosition(position)

        override fun scrollPosition() = scrollPosition.favoritesScrollPosition()

        override suspend fun read() =
            favoritesRepository.read().map { it.map(mapperDomain) }
    }
}