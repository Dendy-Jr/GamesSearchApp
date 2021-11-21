package com.dendi.android.gamessearchapp.presentation.favorites

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseFavoriteUiMapper : Abstract.FavoriteMapper<FavoriteUi> {
    override fun map(id: Int, thumbnail: String, title: String) =
        FavoriteUi.Base(id = id, thumbnail = thumbnail, title = title)
}