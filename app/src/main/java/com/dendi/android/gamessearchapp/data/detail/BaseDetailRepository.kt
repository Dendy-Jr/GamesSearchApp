package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.data.detail.cache.DetailCache
import com.dendi.android.gamessearchapp.data.detail.cache.DetailCacheDataSource
import com.dendi.android.gamessearchapp.data.detail.cache.DetailCacheMapper
import com.dendi.android.gamessearchapp.data.detail.cloud.DetailCloudDataSource
import com.dendi.android.gamessearchapp.data.favorites.FavoriteCache
import com.dendi.android.gamessearchapp.domain.detail.DetailRepository

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseDetailRepository(
    private val cloudDataSource: DetailCloudDataSource,
    private val cacheDataSource: DetailCacheDataSource,
    private val dataMapper: DetailDataMapper<DetailData>,
    private val cacheMapper: DetailCacheMapper<DetailCache>,
) : DetailRepository {
    override suspend fun saveToFavorite(game: FavoriteCache) =
        cacheDataSource.saveToFavorite(game)

    override suspend fun deleteFromFavorite(game: FavoriteCache) =
        cacheDataSource.deleteFromFavorite(game)

    override suspend fun readId(id: Int): DataDetailState = try {
        val cache = cacheDataSource.readId(id)
        if (cache == null) {
            val responseData = cloudDataSource.readId(id)
            val detailGame = responseData.map(cacheMapper)
            cacheDataSource.save(detailGame)
            DataDetailState.Success(detailGame.map(dataMapper))
        } else {
            DataDetailState.Success(cache.map(dataMapper))
        }
    } catch (e: Exception) {
        DataDetailState.Fail(e)
    }
}



