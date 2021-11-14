package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract


/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GameDomain {

    fun <T> map(mapper: Abstract.ToGameMapper<T>): T

    data class Base(
        private val id: Int,
        private val thumbnail: String,
        private val title: String,
    ) : GameDomain {
        override fun <T> map(mapper: Abstract.ToGameMapper<T>) =
            mapper.map(id, thumbnail = thumbnail, title = title)
    }
}
