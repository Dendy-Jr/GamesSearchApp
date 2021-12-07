package com.dendi.android.gamessearchapp.data.detail.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */

@Entity(tableName = "system_requirements_table")
data class SystemRequirementsCache(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "graphics")
    val graphics: String,
    @ColumnInfo(name = "memory")
    val memory: String,
    @ColumnInfo(name = "os")
    val os: String,
    @ColumnInfo(name = "processor")
    val processor: String,
    @ColumnInfo(name = "storage")
    val storage: String,
) : Abstract.Mapper.SystemRequirementsObject {
    override fun <T> map(mapper: Abstract.SystemRequirementsMapper<T>) =
        mapper.map(
            id = id,
            graphics = graphics,
            memory = memory,
            os = os,
            processor = processor,
            storage = storage)

    companion object {
        val EMPTY = SystemRequirementsCache(0, "", "", "", "", "")
    }
}

