package com.dendi.android.gamessearchapp.domain.games

import androidx.lifecycle.LiveData
import com.dendi.android.gamessearchapp.core.Read
import com.dendi.android.gamessearchapp.data.games.GameData
import com.dendi.android.gamessearchapp.data.games.GamesDataState

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesRepository : Read<GamesDataState> {

    fun searchGame(searchQuery: String): LiveData<List<GameData>>

}