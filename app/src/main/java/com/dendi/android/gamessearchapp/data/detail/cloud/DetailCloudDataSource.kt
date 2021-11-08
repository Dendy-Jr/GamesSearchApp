package com.dendi.android.gamessearchapp.data.detail.cloud

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailCloudDataSource {

    suspend fun fetchDetail(id: Int): DetailCloud

    class Base(private val service: DetailService) : DetailCloudDataSource {
        override suspend fun fetchDetail(id: Int) = service.fetchDetail(id).body()!!
    }
}