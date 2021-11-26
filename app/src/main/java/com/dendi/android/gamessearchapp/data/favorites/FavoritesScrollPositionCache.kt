package com.dendi.android.gamessearchapp.data.favorites

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoritesScrollPositionCache {

    fun saveFavoritesScrollPosition(position: Int)
    fun favoritesScrollPosition(): Int
}