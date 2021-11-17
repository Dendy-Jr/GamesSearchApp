package com.dendi.android.gamessearchapp.data.favorites

import com.dendi.android.gamessearchapp.core.Read

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoritesCacheDataSource : Read<List<FavoriteCache>> {

    class Base(
        private val favoriteDao: FavoriteDao,
    ) : FavoritesCacheDataSource {

        override suspend fun read() = favoriteDao.fetchFavorites()
    }
}