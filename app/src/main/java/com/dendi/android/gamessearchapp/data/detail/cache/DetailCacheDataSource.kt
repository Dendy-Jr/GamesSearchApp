package com.dendi.android.gamessearchapp.data.detail.cache

import com.dendi.android.gamessearchapp.core.ReadById
import com.dendi.android.gamessearchapp.core.Save
import com.dendi.android.gamessearchapp.data.detail.DetailData
import com.dendi.android.gamessearchapp.data.favorites.FavoriteCache
import com.dendi.android.gamessearchapp.data.favorites.FavoriteDao

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailCacheDataSource : Save<DetailData>, ReadById<DetailCache> {

    suspend fun saveToFavorite(game: FavoriteCache.Base)

    suspend fun deleteFromFavorite(game: FavoriteCache.Base)

    class Base(
        private val detailDao: DetailDao,
        private val favoriteDao: FavoriteDao,
        private val mapper: DetailDataToCacheMapper<DetailCache.Base>,
    ) : DetailCacheDataSource {
        override suspend fun saveToFavorite(game: FavoriteCache.Base) =
            favoriteDao.saveToFavorite(game)

        override suspend fun deleteFromFavorite(game: FavoriteCache.Base) =
            favoriteDao.deleteFromFavorite(game)

        override suspend fun save(data: DetailData) = detailDao.saveDetail(data.map(mapper))

        override suspend fun readId(id: Int) = detailDao.fetchDetail(id)
    }
}