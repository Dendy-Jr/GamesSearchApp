package com.dendi.android.gamessearchapp.core

import android.app.Application
import androidx.viewbinding.BuildConfig
import com.dendi.android.gamessearchapp.data.BaseGamesRepository
import com.dendi.android.gamessearchapp.data.GameDataMapper
import com.dendi.android.gamessearchapp.data.GameDataToDbMapper
import com.dendi.android.gamessearchapp.data.cache.GameDatabase
import com.dendi.android.gamessearchapp.data.cache.GamesCacheDataSource
import com.dendi.android.gamessearchapp.data.cloud.GameService
import com.dendi.android.gamessearchapp.data.cloud.GamesCloudDataSource
import com.dendi.android.gamessearchapp.domain.BaseGameDataToDomainMapper
import com.dendi.android.gamessearchapp.domain.BaseGamesDataToDomainMapper
import com.dendi.android.gamessearchapp.domain.GamesInteractor
import com.dendi.android.gamessearchapp.presentation.BaseGameDomainToUiMapper
import com.dendi.android.gamessearchapp.presentation.BaseGamesDomainToUiMapper
import com.dendi.android.gamessearchapp.presentation.GameViewModel
import com.dendi.android.gamessearchapp.presentation.GamesCommunication
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

    lateinit var gameViewModel: GameViewModel
    lateinit var gamesInteractor: GamesInteractor

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
        val gameDao = GameDatabase.database(this).gameDao()
        val resourceProvider = ResourceProvider.Base(this)
        val gameDataMapper = GameDataMapper.Base()
        val gameDataToDbMapper = GameDataToDbMapper.Base()
        val gameDataToDomain = BaseGameDataToDomainMapper()
        val gamesDataToDomain = BaseGamesDataToDomainMapper(gameDataToDomain)
        val gameDomainToUiMapper = BaseGameDomainToUiMapper()
        val gamesDomainToUiMapper =
            BaseGamesDomainToUiMapper(resourceProvider, gameDomainToUiMapper)
        val gameDataToDomainMapper = BaseGameDataToDomainMapper()

        val gamesCloudDataSource = GamesCloudDataSource.Base(gameService)
        val gamesCacheDataSource = GamesCacheDataSource.Base(gameDao, gameDataToDbMapper)
        val gamesRepository = BaseGamesRepository(
            gamesCloudDataSource,
            gamesCacheDataSource,
            gameDataMapper
        )

        gamesInteractor = GamesInteractor.Base(gamesRepository,
            gamesDataToDomain, gameDataToDomainMapper)

        val gamesCommunication = GamesCommunication.Base()

        gameViewModel = GameViewModel(
            gamesInteractor, gamesDomainToUiMapper, gameDomainToUiMapper, gamesCommunication
        )
    }
}