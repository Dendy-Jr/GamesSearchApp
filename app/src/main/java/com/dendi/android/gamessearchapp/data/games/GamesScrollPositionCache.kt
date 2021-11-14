package com.dendi.android.gamessearchapp.data.games

/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesScrollPositionCache {

    fun saveGamesScrollPosition(position: Int)
    fun gamesScrollPosition(): Int
}