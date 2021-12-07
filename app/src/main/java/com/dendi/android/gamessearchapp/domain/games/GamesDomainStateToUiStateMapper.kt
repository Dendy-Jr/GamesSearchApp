package com.dendi.android.gamessearchapp.domain.games

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesDomainStateToUiStateMapper<T> : Abstract.Mapper.DomainToUi<List<GameDomain>, T>