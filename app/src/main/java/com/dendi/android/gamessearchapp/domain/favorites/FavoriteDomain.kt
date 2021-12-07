package com.dendi.android.gamessearchapp.domain.favorites

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */

data class FavoriteDomain(
    private val id: Int,
    private val thumbnail: String,
    private val title: String,
) : Abstract.Mapper.FavoriteObject {
    override fun <T> map(mapper: Abstract.FavoriteMapper<T>) =
        mapper.map(id = id, thumbnail = thumbnail, title = title)
}
