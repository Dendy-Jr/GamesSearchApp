package com.dendi.android.gamessearchapp.data.detail.cache

import com.dendi.android.gamessearchapp.core.ReadById
import com.dendi.android.gamessearchapp.core.Save
import com.dendi.android.gamessearchapp.data.detail.DetailData

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailCacheDataSource : Save<DetailData>, ReadById<DetailCache> {

    class Base(
        private val detailDao: DetailDao,
        private val mapper: DetailDataToCacheMapper<DetailCache.Base>,
    ) : DetailCacheDataSource {

        override suspend fun save(data: DetailData) = detailDao.saveDetail(data.map(mapper))

        override suspend fun readId(id: Int) = detailDao.fetchDetail(id)
    }
}