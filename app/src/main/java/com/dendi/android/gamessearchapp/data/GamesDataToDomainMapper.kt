package com.dendi.android.gamessearchapp.data

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.GamesDomain

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesDataToDomainMapper : Abstract.Mapper {
    fun map(games: List<GameData>): GamesDomain
    fun map(e: Exception): GamesDomain
}