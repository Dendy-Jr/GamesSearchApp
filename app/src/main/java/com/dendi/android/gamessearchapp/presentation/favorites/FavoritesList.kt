package com.dendi.android.gamessearchapp.presentation.favorites

import com.dendi.android.gamessearchapp.core.ListMapper

/**
 * @author Dendy-Jr on 15.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoritesList {

    fun map(mapper: ListMapper<FavoriteUi>) = Unit

    data class Base(
        private val games: List<FavoriteUi>
    ) : FavoritesList {
        override fun map(mapper: ListMapper<FavoriteUi>) =
            mapper.map(games)
    }
}