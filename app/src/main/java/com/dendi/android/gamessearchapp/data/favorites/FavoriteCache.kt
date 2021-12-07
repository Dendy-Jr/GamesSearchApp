package com.dendi.android.gamessearchapp.data.favorites

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */

@Entity(tableName = "favorite_table")
data class FavoriteCache(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "title")
    val title: String,
) : Abstract.Mapper.FavoriteObject {
    override fun <T> map(mapper: Abstract.FavoriteMapper<T>) =
        mapper.map(id, thumbnail, title)
}
