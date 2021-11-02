package com.dendi.android.gamessearchapp.domain

import com.dendi.android.gamessearchapp.data.GameDataToDomainMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGameDataToDomainMapper : GameDataToDomainMapper {
    override fun map(
        id: Int,
        thumbnail: String,
        title: String,
    ) = GameDomain(
        id = id,
        thumbnail = thumbnail,
        title = title
    )
}