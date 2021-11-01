package com.dendi.android.gamessearchapp.domain

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.GameUi

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
data class GameDomain(
    private val id: Int,
    private val thumbnail: String,
    private val title: String,
) : Abstract.Object<GameUi, GameDomainToUiMapper> {
    override fun map(mapper: GameDomainToUiMapper): GameUi {
        return mapper.map(
            id = id,
            thumbnail = thumbnail,
            title = title
        )
    }
}