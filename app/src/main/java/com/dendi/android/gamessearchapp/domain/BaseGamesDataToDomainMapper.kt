package com.dendi.android.gamessearchapp.domain

import com.dendi.android.gamessearchapp.data.GameData
import com.dendi.android.gamessearchapp.data.GameDataToDomainMapper
import com.dendi.android.gamessearchapp.data.GamesDataToDomainMapper

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGamesDataToDomainMapper(private val gameMapper: GameDataToDomainMapper) :
    GamesDataToDomainMapper {
    override fun map(games: List<GameData>) = GamesDomain.Success(games, gameMapper)

    override fun map(e: Exception) = GamesDomain.Fail(e)
}