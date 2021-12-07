package com.dendi.android.gamessearchapp.domain.favorites

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseFavoriteDomainMapper : Abstract.FavoriteMapper<FavoriteDomain> {
    override fun map(id: Int, thumbnail: String, title: String) =
        FavoriteDomain(id = id, thumbnail = thumbnail, title = title)
}