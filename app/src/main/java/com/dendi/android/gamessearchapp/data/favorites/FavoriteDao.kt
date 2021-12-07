package com.dendi.android.gamessearchapp.data.favorites

import androidx.room.*

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */

@Dao
interface FavoriteDao{

    @Query("select * from favorite_table")
    suspend fun fetchFavorites(): List<FavoriteCache>

    @Insert(entity = FavoriteCache::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToFavorite(game: FavoriteCache)

    @Delete
    suspend fun deleteFromFavorite(game: FavoriteCache)
}