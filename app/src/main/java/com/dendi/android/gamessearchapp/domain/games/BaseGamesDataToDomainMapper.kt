package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.GameData
import retrofit2.HttpException
import java.net.UnknownHostException


/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGamesDataToDomainMapper(
    private val mapper: Abstract.GameMapper<GameDomain>,
) : Abstract.GamesDataToDomainMapper<GamesDomain> {
    override fun map(games: List<GameData>) = GamesDomain.Success(games.map { it.map(mapper) })

    override fun map(exception: Exception) = GamesDomain.Fail(
        when(exception) {
            is UnknownHostException -> ErrorType.NO_CONNECTION
            is HttpException -> ErrorType.SERVICE_UNAVAILABLE
            else -> ErrorType.GENERIC_ERROR
        }
    )
}