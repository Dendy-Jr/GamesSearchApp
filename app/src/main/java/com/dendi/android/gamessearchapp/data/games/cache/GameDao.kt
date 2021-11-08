package com.dendi.android.gamessearchapp.data.games.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
@Dao
interface GameDao {

    @Query("select * from game_table")
    suspend fun fetchAllGames(): List<GameDb>

    @Insert(entity = GameDb::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGamesToDb(games: List<GameDb>)

    @Query("select * from game_table where title like :searchQuery")
    fun searchGame(searchQuery: String): LiveData<List<GameDb>>
}