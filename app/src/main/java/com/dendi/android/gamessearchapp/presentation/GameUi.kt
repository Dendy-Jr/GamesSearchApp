package com.dendi.android.gamessearchapp.presentation

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class GameUi : Abstract.Object<Unit, UiResultMapper> {

    override fun map(mapper: UiResultMapper) = Unit

    object Progress : GameUi()

    data class Base(
        private val id: Int,
        private val thumbnail: String,
        private val title: String,
    ) : GameUi() {
        override fun map(mapper: UiResultMapper) {
            mapper.map(
                id,
                thumbnail,
                title)
        }
    }

    data class Fail(private val message: String) : GameUi() {
        override fun map(mapper: UiResultMapper) = mapper.map(message)
    }

}

interface UiResultMapper : Abstract.Mapper {
    fun map(
        id: Int,
        thumbnail: String,
        title: String,
    )
    fun map(message: String)
}