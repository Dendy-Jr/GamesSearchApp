package com.dendi.android.gamessearchapp.domain.detail

import com.dendi.android.gamessearchapp.data.detail.DetailHandlerData

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailRepository {
    suspend fun fetchDetail(id: Int): DetailHandlerData
}