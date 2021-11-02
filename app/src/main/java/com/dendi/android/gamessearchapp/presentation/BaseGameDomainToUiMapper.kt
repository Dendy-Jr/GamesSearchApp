package com.dendi.android.gamessearchapp.presentation

import com.dendi.android.gamessearchapp.domain.GameDomainToUiMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGameDomainToUiMapper : GameDomainToUiMapper {
    override fun map(
        id: Int,
        thumbnail: String,
        title: String,
    ) = GameUi.Base(
        id = id,
        thumbnail = thumbnail,
        title = title
    )
}