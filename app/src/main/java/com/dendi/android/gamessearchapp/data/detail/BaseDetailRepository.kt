package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.data.detail.cache.DetailCacheDataSource
import com.dendi.android.gamessearchapp.data.detail.cloud.DetailCloudDataSource
import com.dendi.android.gamessearchapp.domain.detail.DetailRepository

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseDetailRepository(
    private val detailCloudDataSource: DetailCloudDataSource,
    private val detailCacheDataSource: DetailCacheDataSource,
    private val mapper: DetailDataMapper<DetailData.Base>,
) : DetailRepository {
    override suspend fun readId(id: Int): DataDetailState = try {
        val cache = detailCacheDataSource.readId(id)
        if (cache == null) {
            val responseData = detailCloudDataSource.readId(id)
            val detailGame = responseData.map(mapper)
            detailCacheDataSource.save(detailGame)
            DataDetailState.Success(detailGame)
        } else {
            DataDetailState.Success(cache.map(mapper))
        }
    } catch (e: Exception) {
        DataDetailState.Fail(e)
    }
}



