package com.dendi.android.gamessearchapp.data.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoriteCache : Abstract.CacheObject {

    fun <T> map(mapper: Abstract.FavoriteMapper<T>): T

    @Entity(tableName = "favorite_table")
    class Base(
        @ColumnInfo(name = "id")
        @PrimaryKey
        val id: Int,
        @ColumnInfo(name = "thumbnail")
        val thumbnail: String,
        @ColumnInfo(name = "title")
        val title: String,
        @ColumnInfo(name = "platform")
        val platform: String,
        @ColumnInfo(name = "developer")
        val developer: String,
    ) : FavoriteCache {
        override fun <T> map(mapper: Abstract.FavoriteMapper<T>) =
            mapper.map(
                id = id,
                thumbnail = thumbnail,
                title = title,
                platform = platform,
                developer = developer
            )
    }
}
