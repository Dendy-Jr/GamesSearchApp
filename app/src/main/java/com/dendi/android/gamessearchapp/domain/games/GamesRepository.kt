package com.dendi.android.gamessearchapp.domain.games

import androidx.lifecycle.LiveData
import com.dendi.android.gamessearchapp.data.games.GameData
import com.dendi.android.gamessearchapp.data.games.GamesData

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesRepository {
    suspend fun fetchGames(): GamesData

    fun searchGame(searchQuery: String): LiveData<List<GameData>>
}