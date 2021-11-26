package com.dendi.android.gamessearchapp.presentation.games.filter

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dendi.android.gamessearchapp.core.ResourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

/**
 * @author Dendy-Jr on 19.11.2021
 * olehvynnytskyi@gmail.com
 */


class DataStoreFilter(resourceProvider: ResourceProvider) {

    private object PreferenceKey {
        val GENRE_TYPE = stringPreferencesKey("genreType")
    }

    private val dataStore: DataStore<Preferences> = resourceProvider.provideDataStore("PREFERENCES_NAME")

    suspend fun saveGenreType(
        genreType: GenreType
    ) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.GENRE_TYPE] = when (genreType) {
                GenreType.MMORPG -> GenreType.MMORPG.value
                GenreType.SHOOTER -> GenreType.SHOOTER.value
                GenreType.STRATEGY -> GenreType.STRATEGY.value
                GenreType.MOBA -> GenreType.MOBA.value
                GenreType.RACING -> GenreType.RACING.value
                GenreType.SPORTS -> GenreType.SPORTS.value
                GenreType.SOCIAL -> GenreType.SOCIAL.value
                GenreType.CARD -> GenreType.CARD.value
                GenreType.BATTLE_ROYALE -> GenreType.BATTLE_ROYALE.value
                GenreType.MMO -> GenreType.MMO.value
                GenreType.FANTASY -> GenreType.FANTASY.value
                GenreType.FIGHTING -> GenreType.FIGHTING.value
                GenreType.ACTION_RPG -> GenreType.ACTION_RPG.value
                else -> ""
            }
        }
    }

    val readGenreType: Flow<GenreType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                exception.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            when (preferences[PreferenceKey.GENRE_TYPE] ?: "") {
                GenreType.MMORPG.value -> GenreType.MMORPG
                GenreType.SHOOTER.value -> GenreType.SHOOTER
                GenreType.STRATEGY.value -> GenreType.STRATEGY
                GenreType.MOBA.value -> GenreType.MOBA
                GenreType.RACING.value -> GenreType.RACING
                GenreType.SPORTS.value -> GenreType.SPORTS
                GenreType.SOCIAL.value -> GenreType.SOCIAL
                GenreType.CARD.value -> GenreType.CARD
                GenreType.BATTLE_ROYALE.value -> GenreType.BATTLE_ROYALE
                GenreType.MMO.value -> GenreType.MMO
                GenreType.FANTASY.value -> GenreType.FANTASY
                GenreType.FIGHTING.value -> GenreType.FIGHTING
                GenreType.ACTION_RPG.value -> GenreType.ACTION_RPG
                else -> GenreType.ALL
            }
        }
}