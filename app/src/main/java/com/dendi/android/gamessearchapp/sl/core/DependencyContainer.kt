package com.dendi.android.gamessearchapp.sl.core

import com.dendi.android.gamessearchapp.sl.detail.DetailModule
import com.dendi.android.gamessearchapp.sl.games.GamesModule

/**
 * @author Dendy-Jr on 12.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DependencyContainer {

    fun module(feature: Feature): BaseModule<*>

    class Base(
        private val coreModule: CoreModule,
    ) : DependencyContainer {

        override fun module(feature: Feature): BaseModule<*> = when (feature) {
            is Feature.Games -> GamesModule(coreModule)
            is Feature.Detail -> DetailModule(coreModule)
        }
    }
}