package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ReadById
import com.dendi.android.gamessearchapp.data.detail.DetailDataStateToDomainMapper
import com.dendi.android.gamessearchapp.data.favorites.FavoriteCache
import com.dendi.android.gamessearchapp.domain.favorites.FavoriteDomain
import com.dendi.android.gamessearchapp.presentation.core.ScrollPosition


/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailInteractor : ScrollPosition, ReadById<DetailDomainState> {

    suspend fun saveToFavorite(game: FavoriteDomain.Base)

    suspend fun deleteFromFavorite(game: FavoriteDomain.Base)

    class Base(
        private val detailRepository: DetailRepository,
        private val detailDomainMapper: DetailDataStateToDomainMapper<DetailDomainState>,
        private val mapperCache: Abstract.FavoriteMapper<FavoriteCache.Base>,
    ) : DetailInteractor {
        override suspend fun saveToFavorite(game: FavoriteDomain.Base) =
            detailRepository.saveToFavorite(game.map(mapperCache))

        override suspend fun deleteFromFavorite(game: FavoriteDomain.Base) =
            detailRepository.deleteFromFavorite(game.map(mapperCache))

        override suspend fun readId(id: Int) =
            detailRepository.readId(id).map(detailDomainMapper)

    }
}