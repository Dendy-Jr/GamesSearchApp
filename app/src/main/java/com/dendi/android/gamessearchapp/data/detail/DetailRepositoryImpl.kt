package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.detail.cache.DetailCacheDataSource
import com.dendi.android.gamessearchapp.data.detail.cloud.DetailCloudDataSource
import com.dendi.android.gamessearchapp.domain.detail.DetailRepository

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailRepositoryImpl(
    private val detailCloudDataSource: DetailCloudDataSource,
    private val detailCacheDataSource: DetailCacheDataSource,
    private val mapper: Abstract.DetailDataMapper<DetailData>,
) : DetailRepository {
    override suspend fun fetchDetail(id: Int) = try {
        val cache = detailCacheDataSource.fetchDetail(id)
        if (cache == null) {
            val responseData = detailCloudDataSource.fetchDetail(id)
            val detailGame = responseData.map(mapper)
            detailCacheDataSource.saveDetail(detailGame)
            DetailHandlerData.Success(detailGame)
        } else {
            DetailHandlerData.Success(cache.map(mapper))
        }
    } catch (e: Exception) {
        DetailHandlerData.Fail(e)
    }
}



