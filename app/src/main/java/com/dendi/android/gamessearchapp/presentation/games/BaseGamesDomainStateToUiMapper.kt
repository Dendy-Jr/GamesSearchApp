package com.dendi.android.gamessearchapp.presentation.games

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.core.ErrorType
import com.dendi.android.gamessearchapp.domain.games.GameDomain
import com.dendi.android.gamessearchapp.domain.games.GamesDomainStateToUiMapper
import com.dendi.android.gamessearchapp.presentation.core.BaseDomainToUiMapper

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class BaseGamesDomainStateToUiMapper(
    resourceProvider: ResourceProvider,
    private val mapper: Abstract.ToGameMapper<GameUi>,
) : BaseDomainToUiMapper<List<GameDomain>, List<GameUi>>(resourceProvider),
    GamesDomainStateToUiMapper<List<GameUi>> {

    override fun map(data: List<GameDomain>) = data.map { it.map(mapper) }

    override fun map(errorType: ErrorType) =
        listOf(GameUi.Fail(errorMessage(errorType)))

}