package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Communication

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCommunication : Communication<GamesUiState> {
    class Base : Communication.Base<GamesUiState>(), GamesCommunication
}