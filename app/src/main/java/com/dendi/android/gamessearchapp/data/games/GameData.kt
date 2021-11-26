package com.dendi.android.gamessearchapp.data.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GameData : Abstract.DataObject {

    fun <T> map(mapper: Abstract.ToGameMapper<T>): T
    fun <T : Abstract.CacheObject> map(mapper: Abstract.ToGameMapper<T>): T

    data class Base(
        private val id: Int,
        private val thumbnail: String,
        private val title: String,
        private val shortDescription: String,
    ) : GameData {
        override fun <T> map(mapper: Abstract.ToGameMapper<T>) =
            mapper.map(
                id = id,
                thumbnail = thumbnail,
                title = title,
                shortDescription = shortDescription
            )

        override fun <T : Abstract.CacheObject> map(mapper: Abstract.ToGameMapper<T>) =
            mapper.map(
                id = id,
                thumbnail = thumbnail,
                title = title,
                shortDescription = shortDescription
            )
    }
}


