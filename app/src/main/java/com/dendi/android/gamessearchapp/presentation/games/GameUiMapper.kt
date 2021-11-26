package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GameUiMapper<T> : Abstract.Mapper {
    fun map(id: Int, thumbnail: String, title: String, shortDescription: String): T

    fun map(message: String): T
}