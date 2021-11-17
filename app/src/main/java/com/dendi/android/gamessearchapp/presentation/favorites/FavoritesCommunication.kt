package com.dendi.android.gamessearchapp.presentation.favorites

import com.dendi.android.gamessearchapp.core.Communication

/**
 * @author Dendy-Jr on 14.11.2021
 * olehvynnytskyi@gmail.com
 */
interface FavoritesCommunication : Communication<FavoritesList> {
    class Base : Communication.Base<FavoritesList>(), FavoritesCommunication
}