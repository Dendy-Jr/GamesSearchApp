package com.dendi.android.gamessearchapp.sl.core

import android.content.Context
import com.dendi.android.gamessearchapp.core.ResourceProvider
import com.dendi.android.gamessearchapp.core.ScrollPositionCache
import com.dendi.android.gamessearchapp.data.core.GamesDatabase
import com.dendi.android.gamessearchapp.data.detail.cache.DetailDao
import com.dendi.android.gamessearchapp.data.favorites.FavoriteDao
import com.dendi.android.gamessearchapp.data.games.cache.GameDao
import com.dendi.android.gamessearchapp.presentation.games.filter.DataStoreRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Dendy-Jr on 12.11.2021
 * olehvynnytskyi@gmail.com
 */
class CoreModule {

    private lateinit var retrofit: Retrofit
    lateinit var resourceProvider: ResourceProvider
    lateinit var client: OkHttpClient
    lateinit var gameDao: GameDao
    lateinit var detailDao: DetailDao
    lateinit var favoriteDao: FavoriteDao
    lateinit var scrollPositionCache: ScrollPositionCache
    lateinit var dataStoreFilter: DataStoreRepository

    fun init(context: Context) {

        client = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        resourceProvider = ResourceProvider.Base(context)
        gameDao = GamesDatabase.database(context).gameDao()
        detailDao = GamesDatabase.database(context).detailDao()
        favoriteDao = GamesDatabase.database(context).favoriteDao()
        dataStoreFilter = DataStoreRepository(resourceProvider)

        scrollPositionCache = ScrollPositionCache.Base(resourceProvider)
    }

    fun <T> networkService(clazz: Class<T>): T = retrofit.create(clazz)

    private companion object {
        const val BASE_URL = "https://www.freetogame.com/api/"
    }

}