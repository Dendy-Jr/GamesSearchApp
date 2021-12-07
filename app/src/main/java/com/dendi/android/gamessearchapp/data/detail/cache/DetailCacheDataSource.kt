package com.dendi.android.gamessearchapp.data.detail.cache

import com.dendi.android.gamessearchapp.core.ReadById
import com.dendi.android.gamessearchapp.core.Save
import com.dendi.android.gamessearchapp.data.favorites.FavoriteCache
import com.dendi.android.gamessearchapp.data.favorites.FavoriteDao

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailCacheDataSource : Save<DetailCache>, ReadById<DetailCache> {

    suspend fun saveToFavorite(game: FavoriteCache)

    suspend fun deleteFromFavorite(game: FavoriteCache)

    class Base(
        private val detailDao: DetailDao,
        private val favoriteDao: FavoriteDao,
    ) : DetailCacheDataSource {
        override suspend fun saveToFavorite(game: FavoriteCache) =
            favoriteDao.saveToFavorite(game)

        override suspend fun deleteFromFavorite(game: FavoriteCache) =
            favoriteDao.deleteFromFavorite(game)

        override suspend fun save(data: DetailCache) = detailDao.saveDetail(data)

        override suspend fun readId(id: Int) = detailDao.fetchDetail(id)
    }
}