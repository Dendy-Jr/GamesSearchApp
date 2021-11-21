package com.dendi.android.gamessearchapp.data.favorites

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseFavoriteCacheMapper : Abstract.FavoriteMapper<FavoriteCache.Base> {
    override fun map(id: Int, thumbnail: String, title: String) =
        FavoriteCache.Base(id = id, thumbnail = thumbnail, title = title)
}