package com.dendi.android.gamessearchapp.data.games.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract


/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */

interface GameCache : Abstract.CacheObject {

    fun <T> map(mapper: Abstract.ToGameMapper<T>): T

    @Entity(tableName = "game_table")
    data class Base(
        @ColumnInfo(name = "id")
        @PrimaryKey
        val id: Int,
        @ColumnInfo(name = "thumbnail")
        val thumbnail: String,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "genre")
        val genre: String,
        @ColumnInfo(name = "release_date")
        val releaseDate: String,
    ) : GameCache {
        override fun <T> map(mapper: Abstract.ToGameMapper<T>) =
            mapper.map(
                id = id,
                thumbnail = thumbnail,
                title = title,
                genre = genre,
                releaseDate = releaseDate
            )
    }
}
