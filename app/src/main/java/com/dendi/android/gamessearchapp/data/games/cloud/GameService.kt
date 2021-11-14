package com.dendi.android.gamessearchapp.data.games.cloud

import retrofit2.Response
import retrofit2.http.GET

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GameService {
    @GET("games")
    suspend fun fetchGames(): Response<List<GameCloud.Base>>
}