package com.dendi.android.gamessearchapp.presentation.favorites

import com.dendi.android.gamessearchapp.core.Communication

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoritesCommunication : Communication<List<FavoriteUi>> {
    class Base : Communication.Base<List<FavoriteUi>>(), FavoritesCommunication
}