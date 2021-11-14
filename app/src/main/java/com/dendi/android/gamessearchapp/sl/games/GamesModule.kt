package com.dendi.android.gamessearchapp.sl.games

import com.dendi.android.gamessearchapp.data.games.BaseGamesRepository
import com.dendi.android.gamessearchapp.data.games.BaseToGameCacheMapper
import com.dendi.android.gamessearchapp.data.games.BaseToGameDataMapper
import com.dendi.android.gamessearchapp.data.games.cache.GamesCacheDataSource
import com.dendi.android.gamessearchapp.data.games.cloud.GameService
import com.dendi.android.gamessearchapp.data.games.cloud.GamesCloudDataSource
import com.dendi.android.gamessearchapp.domain.games.*
import com.dendi.android.gamessearchapp.presentation.games.BaseGamesDomainStateToUiMapper
import com.dendi.android.gamessearchapp.presentation.games.BaseToGameUiMapper
import com.dendi.android.gamessearchapp.presentation.games.GamesCommunication
import com.dendi.android.gamessearchapp.presentation.games.GamesViewModel
import com.dendi.android.gamessearchapp.sl.core.BaseModule
import com.dendi.android.gamessearchapp.sl.core.CoreModule

/**
 * @author Dendy-Jr on 12.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesModule(
    private val coreModule: CoreModule,
) : BaseModule<GamesViewModel> {

    override fun viewModel(): GamesViewModel {

        val gamesInterceptor = GamesInteractor.Base(
            BaseGamesRepository(
                GamesCloudDataSource.Base(
                    coreModule.networkService(GameService::class.java)
                ),
                GamesCacheDataSource.Base(
                    coreModule.gameDao,
                    BaseToGameCacheMapper()
                ),
                BaseToGameDataMapper()
            ),
            BaseGamesDataStateToDomainMapper(BaseToGameDomainMapper()),
            BaseToGameDomainMapper(),
            coreModule.scrollPositionCache
        )
        val communication = GamesCommunication.Base()

        return GamesViewModel(
            gamesInterceptor,
            BaseGamesDomainStateToUiMapper(
                coreModule.resourceProvider,
                BaseToGameUiMapper()
            ),
            communication
        )
    }
}