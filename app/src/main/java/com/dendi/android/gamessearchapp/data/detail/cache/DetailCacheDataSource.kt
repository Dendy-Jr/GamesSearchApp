package com.dendi.android.gamessearchapp.data.detail.cache

import com.dendi.android.gamessearchapp.data.detail.DbObject
import com.dendi.android.gamessearchapp.data.detail.DetailData

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailCacheDataSource {

    suspend fun fetchDetail(id: Int): DetailDb

    suspend fun saveDetail(detail: DetailData)

    class Base(
        private val detailDao: DetailDao,
        private val mapper: DbObject<DetailData, DetailDb>,
    ) : DetailCacheDataSource {
        override suspend fun fetchDetail(id: Int) = detailDao.fetchDetail(id)

        override suspend fun saveDetail(detail: DetailData) =
            detailDao.saveDetail(mapper.map(detail))
    }
}