package com.dendi.android.gamessearchapp.domain

import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.presentation.GameUi

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GameDomainToUiMapper : Abstract.Mapper {
    fun map(
        id: Int,
        thumbnail: String,
        title: String,
    ): GameUi
}