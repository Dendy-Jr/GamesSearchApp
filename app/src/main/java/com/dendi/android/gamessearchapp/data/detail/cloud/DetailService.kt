package com.dendi.android.gamessearchapp.data.detail.cloud

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailService {

    @GET("game")
    suspend fun fetchDetail(@Query("id") id: Int): Response<DetailCloud.Base>
}