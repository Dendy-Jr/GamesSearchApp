package com.dendi.android.gamessearchapp.data.favorites

import com.dendi.android.gamessearchapp.domain.favorites.FavoritesRepository

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseFavoritesRepository(
    private val cacheDataSource: FavoritesCacheDataSource,
) : FavoritesRepository {
    override suspend fun read() = cacheDataSource.read()
}