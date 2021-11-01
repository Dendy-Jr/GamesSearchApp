package com.dendi.android.gamessearchapp.data.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.GameData

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
@Entity(tableName = "game_table")
data class GameDb(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "thumbnail")
    val thumbnail: String = "",
    @ColumnInfo(name = "title")
    val title: String = "",
) : Abstract.Object<GameData, Abstract.Mapper.Empty>
//todo fix