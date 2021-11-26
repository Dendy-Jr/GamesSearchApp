package com.dendi.android.gamessearchapp.domain.favorites

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoriteDomain {

    fun <T> map(mapper: Abstract.FavoriteMapper<T>): T

    class Base(
        private val id: Int,
        private val thumbnail: String,
        private val title: String
    ) : FavoriteDomain {
        override fun <T> map(mapper: Abstract.FavoriteMapper<T>) =
            mapper.map(id = id, thumbnail = thumbnail, title = title)
    }
}