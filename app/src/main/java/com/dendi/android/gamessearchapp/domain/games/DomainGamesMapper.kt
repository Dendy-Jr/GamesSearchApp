package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.GameData


/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class DomainGamesMapper(
    private val domainMapper: Abstract.GameMapper<GameDomain>,
) : Abstract.GamesDomainMapper<GamesDomain> {
    override fun map(games: List<GameData>) = GamesDomain.Success(games, domainMapper)

    override fun map(exception: Exception) = GamesDomain.Fail(exception)
}