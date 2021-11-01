package com.dendi.android.gamessearchapp.domain

import com.dendi.android.gamessearchapp.data.GamesData

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesRepository {
    suspend fun fetchGames(): GamesData
}