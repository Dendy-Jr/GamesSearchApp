package com.dendi.android.gamessearchapp.core

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.dendi.android.gamessearchapp.data.games.BaseGamesRepository
import com.dendi.android.gamessearchapp.data.core.GamesDatabase
import com.dendi.android.gamessearchapp.data.detail.DetailRepositoryImpl
import com.dendi.android.gamessearchapp.data.detail.DbObjectMapperBase
import com.dendi.android.gamessearchapp.data.detail.DetailDataMapperBase
import com.dendi.android.gamessearchapp.data.detail.cache.DetailCacheDataSource
import com.dendi.android.gamessearchapp.data.detail.cloud.DetailCloudDataSource
import com.dendi.android.gamessearchapp.data.detail.cloud.DetailService
import com.dendi.android.gamessearchapp.data.games.DataGameMapper
import com.dendi.android.gamessearchapp.data.games.DbGameMapper
import com.dendi.android.gamessearchapp.data.games.cache.GamesCacheDataSource
import com.dendi.android.gamessearchapp.data.games.cloud.GameService
import com.dendi.android.gamessearchapp.data.games.cloud.GamesCloudDataSource
import com.dendi.android.gamessearchapp.domain.detail.DetailDomainMapperBase
import com.dendi.android.gamessearchapp.domain.detail.DetailInteractor
import com.dendi.android.gamessearchapp.domain.detail.HandlerDomainMapperBase
import com.dendi.android.gamessearchapp.domain.games.*
import com.dendi.android.gamessearchapp.presentation.detail.DetailCommunication
import com.dendi.android.gamessearchapp.presentation.detail.DetailUiMapperBase
import com.dendi.android.gamessearchapp.presentation.detail.DetailViewModel
import com.dendi.android.gamessearchapp.presentation.detail.HandlerUiMapperBase
import com.dendi.android.gamessearchapp.presentation.games.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesApp : Application() {

    lateinit var gameViewModel: GamesViewModel
    private lateinit var gamesInteractor: GamesInteractor

    lateinit var detailViewModel: DetailViewModel
    private lateinit var detailInteractor: DetailInteractor

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.freetogame.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        val gameService = retrofit.create(GameService::class.java)
        val detailService = retrofit.create(DetailService::class.java)

        val gameDao = GamesDatabase.database(this).gameDao()
        val detailDao = GamesDatabase.database(this).detailDao()

        val resourceProvider = ResourceProvider.Base(this)

        val gameDataMapper = DataGameMapper()
        val detailDataMapper = DetailDataMapperBase()
        val detailDomainMapperBase = DetailDomainMapperBase()
        val handlerDomainBase = HandlerDomainMapperBase(detailDomainMapperBase)

        val dbGameMapper = DbGameMapper()
        val domainGameMapper = DomainGameMapper()
        val domainGamesMapper = BaseGamesDataToDomainMapper(domainGameMapper)
        val uiGameMapper = UiGameMapper()
        val uiGamesMapper =
            BaseGamesDomainToUiMapper(resourceProvider,uiGameMapper)

        val detailDbMapper = DbObjectMapperBase()

        val gamesCloudDataSource = GamesCloudDataSource.Base(gameService)
        val gamesCacheDataSource = GamesCacheDataSource.Base(gameDao, dbGameMapper)
        val detailCloudDataSource = DetailCloudDataSource.Base(detailService)
        val detailCacheDataSource = DetailCacheDataSource.Base(detailDao, detailDbMapper)
        val gamesRepository = BaseGamesRepository(
            gamesCloudDataSource,
            gamesCacheDataSource,
            gameDataMapper
        )
        val detailUiMapper = DetailUiMapperBase()
        val handlerUiMapper = HandlerUiMapperBase(detailUiMapper, resourceProvider)

        val detailRepository =
            DetailRepositoryImpl(detailCloudDataSource, detailCacheDataSource, detailDataMapper)

        gamesInteractor = GamesInteractor.Base(gamesRepository,
            domainGamesMapper, domainGameMapper)

        detailInteractor = DetailInteractor.Base(detailRepository, handlerDomainBase)

        val gamesCommunication = GamesCommunication.Base()
        val detailCommunication = DetailCommunication.Base()

        gameViewModel = GamesViewModel(
            gamesInteractor, uiGamesMapper, uiGameMapper, gamesCommunication
        )

        detailViewModel = DetailViewModel(detailInteractor, detailCommunication, handlerUiMapper)
    }
}