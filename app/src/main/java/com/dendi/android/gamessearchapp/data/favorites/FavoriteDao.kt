package com.dendi.android.gamessearchapp.data.favorites

import androidx.room.*

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */

@Dao
interface FavoriteDao {

    @Query("select * from favorite_table")
    suspend fun fetchFavorites(): List<FavoriteCache.Base>

    @Insert(entity = FavoriteCache.Base::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToFavorite(game: FavoriteCache.Base)

    @Delete
    suspend fun deleteFromFavorite(game: FavoriteCache.Base)
}