package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.data.games.GameData
import com.dendi.android.gamessearchapp.domain.core.BaseDataToDomainMapper


/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGamesDataStateToDomainMapper(
    private val mapper: Abstract.ToGameMapper<GameDomain>,
) : BaseDataToDomainMapper<List<GameData>, GamesDomainState>(),
    GamesDataStateToDomainMapper<GamesDomainState> {
    override fun map(data: List<GameData>) =
        GamesDomainState.Success(data.map { it.map(mapper) })

    override fun map(e: Exception) = GamesDomainState.Fail(errorType(e))
}