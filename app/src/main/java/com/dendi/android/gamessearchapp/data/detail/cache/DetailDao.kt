package com.dendi.android.gamessearchapp.data.detail.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */

@Dao
interface DetailDao {

    @Query("select * from detail_table where id = :id")
    suspend fun fetchDetail(id: Int): DetailCache.Base

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveDetail(detail: DetailCache.Base)
}