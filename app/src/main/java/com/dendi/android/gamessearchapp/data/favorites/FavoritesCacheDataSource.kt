package com.dendi.android.gamessearchapp.data.favorites

import com.dendi.android.gamessearchapp.core.Show

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoritesCacheDataSource : Show<List<FavoriteCache>> {

    class Base(
        private val favoriteDao: FavoriteDao,
    ) : FavoritesCacheDataSource {

        override suspend fun show() = favoriteDao.fetchFavorites()
    }
}