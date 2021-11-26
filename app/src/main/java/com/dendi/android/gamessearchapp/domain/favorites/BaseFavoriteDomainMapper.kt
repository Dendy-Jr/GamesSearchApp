package com.dendi.android.gamessearchapp.domain.favorites

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseFavoriteDomainMapper : Abstract.FavoriteMapper<FavoriteDomain.Base> {
    override fun map(id: Int, thumbnail: String, title: String) =
        FavoriteDomain.Base(id = id, thumbnail = thumbnail, title = title)
}