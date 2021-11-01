package com.dendi.android.gamessearchapp.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
@Database(
    entities = [GameDb::class],
    version = 1,
    exportSchema = false
)
abstract class GameDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        private var instance: GameDatabase? = null

        @Synchronized
        fun database(context: Context): GameDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game_db",
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}