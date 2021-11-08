package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */

sealed class GameUi : Abstract.Object.UnitObject<Abstract.AdapterGameMapper> {

    override fun map(mapper: Abstract.AdapterGameMapper) = Unit

    open fun map(listener: GamesAdapter.DetailOnClickListener) = Unit

    object Progress : GameUi() {
        override fun map(mapper: Abstract.AdapterGameMapper) = Unit
    }

    data class Base(
        private var id: Int,
        private val thumbnail: String,
        private val title: String,
    ) : GameUi() {
        override fun map(mapper: Abstract.AdapterGameMapper) = mapper.map(
            id = id, thumbnail = thumbnail, title = title)

        override fun map(listener: GamesAdapter.DetailOnClickListener) = listener.onClick(id)
    }


    data class Fail(private val message: String) : GameUi() {
        override fun map(mapper: Abstract.AdapterGameMapper) {
            mapper.map(message = message)
        }
    }
}

