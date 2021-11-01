package com.dendi.android.gamessearchapp.presentation

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GameUi : Abstract.Object<Unit, Abstract.Mapper.Empty> {

    object Progress : GameUi()

    data class Base(
        private val id: Int,
        private val thumbnail: String,
        private val title: String,
    ) : GameUi()

    data class Fail(private val message: String) : GameUi()
}
//todo fix