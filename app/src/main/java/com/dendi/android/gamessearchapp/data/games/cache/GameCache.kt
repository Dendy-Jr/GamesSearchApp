package com.dendi.android.gamessearchapp.data.games.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract


/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */

@Entity(tableName = "game_table")
data class GameCache(
    @ColumnInfo(name = "id")
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "short_description")
    val shortDescription: String,
) : Abstract.Mapper.GamesObject {
    override fun <T> map(mapper: Abstract.GameMapper<T>) =
        mapper.map(
            id = id,
            thumbnail = thumbnail,
            title = title,
            shortDescription = shortDescription
        )
}
