package com.dendi.android.gamessearchapp.data

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.GameDomain

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GameDataToDomainMapper : Abstract.Mapper {

    fun map(
        id: Int,
        thumbnail: String,
        title: String,
    ): GameDomain
}