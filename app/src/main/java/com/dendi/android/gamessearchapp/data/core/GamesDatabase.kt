package com.dendi.android.gamessearchapp.data.core

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dendi.android.gamessearchapp.data.detail.cache.*
import com.dendi.android.gamessearchapp.data.favorites.FavoriteCache
import com.dendi.android.gamessearchapp.data.favorites.FavoriteDao
import com.dendi.android.gamessearchapp.data.games.cache.GameDao
import com.dendi.android.gamessearchapp.data.games.cache.GameCache

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
@Database(
    entities = [GameCache::class,
        DetailCache::class,
        ScreenshotCache::class,
        SystemRequirementsCache::class,
        FavoriteCache::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class GamesDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun detailDao(): DetailDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        private var instance: GamesDatabase? = null

        @Synchronized
        fun database(context: Context): GamesDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    GamesDatabase::class.java,
                    "game_db",
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}