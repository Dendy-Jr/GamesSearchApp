package com.dendi.android.gamessearchapp.data.detail.cloud

import com.dendi.android.gamessearchapp.core.ReadById

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailCloudDataSource : ReadById<DetailCloud> {

    class Base(private val service: DetailService) : DetailCloudDataSource {

        override suspend fun readId(id: Int) = service.fetchDetail(id).body()!!
    }
}