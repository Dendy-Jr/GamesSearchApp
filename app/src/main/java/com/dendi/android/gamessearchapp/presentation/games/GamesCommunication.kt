package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Communication

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCommunication : Communication<List<GameUi>> {

  class Base: Communication.Base<List<GameUi>>(), GamesCommunication
}