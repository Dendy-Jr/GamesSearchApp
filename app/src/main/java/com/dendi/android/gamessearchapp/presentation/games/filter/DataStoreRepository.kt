package com.dendi.android.gamessearchapp.presentation.games.filter

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import com.dendi.android.gamessearchapp.core.ResourceProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException

/**
 * @author Dendy-Jr on 19.11.2021
 * olehvynnytskyi@gmail.com
 */


class DataStoreRepository(resourceProvider: ResourceProvider) {

    private val dataStore: DataStore<Preferences> =
        resourceProvider.provideDataStore("PREFERENCES_NAME")

    private object PreferenceKey {
        val GAMES_CATEGORY = stringPreferencesKey("gamesCategory")
        val GAMES_SORT = stringPreferencesKey("gamesSort")
    }

    suspend fun saveGamesSort(gamesSort: GamesSort) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.GAMES_SORT] = when (gamesSort) {
                GamesSort.RELEASE_DATE -> GamesSort.RELEASE_DATE.sort
                GamesSort.POPULARITY -> GamesSort.POPULARITY.sort
                GamesSort.ALPHABETICAL -> GamesSort.ALPHABETICAL.sort
                GamesSort.RELEVANCE -> GamesSort.RELEVANCE.sort
            }
        }
    }

    suspend fun saveGamesCategory(gamesCategory: GamesCategory) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.GAMES_CATEGORY] = when (gamesCategory) {
                GamesCategory.MMORPG -> GamesCategory.MMORPG.value
                GamesCategory.SHOOTER -> GamesCategory.SHOOTER.value
                GamesCategory.STRATEGY -> GamesCategory.STRATEGY.value
                GamesCategory.MOBA -> GamesCategory.MOBA.value
                GamesCategory.RACING -> GamesCategory.RACING.value
                GamesCategory.SPORTS -> GamesCategory.SPORTS.value
                GamesCategory.SOCIAL -> GamesCategory.SOCIAL.value
                GamesCategory.SANDBOX -> GamesCategory.SANDBOX.value
                GamesCategory.OPEN_WORLD -> GamesCategory.OPEN_WORLD.value
                GamesCategory.SURVIVAL -> GamesCategory.SURVIVAL.value
                GamesCategory.PVP -> GamesCategory.PVP.value
                GamesCategory.PVE -> GamesCategory.PVE.value
                GamesCategory.PIXEL -> GamesCategory.PIXEL.value
                GamesCategory.VOXEL -> GamesCategory.VOXEL.value
                GamesCategory.ZOMBIE -> GamesCategory.ZOMBIE.value
                GamesCategory.TURN_BASED -> GamesCategory.TURN_BASED.value
                GamesCategory.FIRST_PERSON -> GamesCategory.FIRST_PERSON.value
                GamesCategory.THIRD_PERSON -> GamesCategory.THIRD_PERSON.value
                GamesCategory.TOP_DOWN -> GamesCategory.TOP_DOWN.value
                GamesCategory.TANK -> GamesCategory.TANK.value
                GamesCategory.SPACE -> GamesCategory.SPACE.value
                GamesCategory.SAILING -> GamesCategory.SAILING.value
                GamesCategory.SIDE_SCROLLER -> GamesCategory.SIDE_SCROLLER.value
                GamesCategory.SUPERHERO -> GamesCategory.SUPERHERO.value
                GamesCategory.PERMADEATH -> GamesCategory.PERMADEATH.value
                GamesCategory.CARD -> GamesCategory.CARD.value
                GamesCategory.BATTLE_ROYALE -> GamesCategory.BATTLE_ROYALE.value
                GamesCategory.MMO -> GamesCategory.MMO.value
                GamesCategory.MMOFPS -> GamesCategory.MMOFPS.value
                GamesCategory.MMOTPS -> GamesCategory.MMOTPS.value
                GamesCategory._3D -> GamesCategory._3D.value
                GamesCategory._2D -> GamesCategory._2D.value
                GamesCategory.ANIME -> GamesCategory.ANIME.value
                GamesCategory.FANTASY -> GamesCategory.FANTASY.value
                GamesCategory.SCI_FI -> GamesCategory.SCI_FI.value
                GamesCategory.FIGHTING -> GamesCategory.FIGHTING.value
                GamesCategory.ACTION_RPG -> GamesCategory.ACTION_RPG.value
                GamesCategory.ACTION -> GamesCategory.ACTION.value
                GamesCategory.MILITARY -> GamesCategory.MILITARY.value
                GamesCategory.MARTIAL_ARTS -> GamesCategory.MARTIAL_ARTS.value
                GamesCategory.FLIGHT -> GamesCategory.FLIGHT.value
                GamesCategory.LOW_SPEC -> GamesCategory.LOW_SPEC.value
                GamesCategory.TOWER_DEFENSE -> GamesCategory.TOWER_DEFENSE.value
                GamesCategory.HORROR -> GamesCategory.HORROR.value
                GamesCategory.MMORTS -> GamesCategory.MMORTS.value
            }
        }
    }

    val readGamesSort: Flow<GamesSort> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                exception.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            when (preferences[PreferenceKey.GAMES_SORT] ?: GamesSort.ALPHABETICAL.sort) {
                GamesSort.RELEASE_DATE.sort -> GamesSort.RELEASE_DATE
                GamesSort.POPULARITY.sort -> GamesSort.POPULARITY
                GamesSort.ALPHABETICAL.sort -> GamesSort.ALPHABETICAL
                GamesSort.RELEVANCE.sort -> GamesSort.RELEVANCE
                else -> GamesSort.RELEASE_DATE
            }
        }

    val readGamesCategory: Flow<GamesCategory> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                exception.printStackTrace()
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            when (preferences[PreferenceKey.GAMES_CATEGORY] ?: GamesCategory.MMORPG.value) {
                GamesCategory.MMORPG.value -> GamesCategory.MMORPG
                GamesCategory.SHOOTER.value -> GamesCategory.SHOOTER
                GamesCategory.STRATEGY.value -> GamesCategory.STRATEGY
                GamesCategory.MOBA.value -> GamesCategory.MOBA
                GamesCategory.RACING.value -> GamesCategory.RACING
                GamesCategory.SPORTS.value -> GamesCategory.SPORTS
                GamesCategory.SOCIAL.value -> GamesCategory.SOCIAL
                GamesCategory.SANDBOX.value -> GamesCategory.SANDBOX
                GamesCategory.OPEN_WORLD.value -> GamesCategory.OPEN_WORLD
                GamesCategory.SURVIVAL.value -> GamesCategory.SURVIVAL
                GamesCategory.PVP.value -> GamesCategory.PVP
                GamesCategory.PVE.value -> GamesCategory.PVE
                GamesCategory.PIXEL.value -> GamesCategory.PIXEL
                GamesCategory.VOXEL.value -> GamesCategory.VOXEL
                GamesCategory.ZOMBIE.value -> GamesCategory.ZOMBIE
                GamesCategory.TURN_BASED.value -> GamesCategory.TURN_BASED
                GamesCategory.FIRST_PERSON.value -> GamesCategory.FIRST_PERSON
                GamesCategory.THIRD_PERSON.value -> GamesCategory.THIRD_PERSON
                GamesCategory.TOP_DOWN.value -> GamesCategory.TOP_DOWN
                GamesCategory.TANK.value -> GamesCategory.TANK
                GamesCategory.SPACE.value -> GamesCategory.SPACE
                GamesCategory.SAILING.value -> GamesCategory.SAILING
                GamesCategory.SIDE_SCROLLER.value -> GamesCategory.SIDE_SCROLLER
                GamesCategory.SUPERHERO.value -> GamesCategory.SUPERHERO
                GamesCategory.PERMADEATH.value -> GamesCategory.PERMADEATH
                GamesCategory.CARD.value -> GamesCategory.CARD
                GamesCategory.BATTLE_ROYALE.value -> GamesCategory.BATTLE_ROYALE
                GamesCategory.MMO.value -> GamesCategory.MMO
                GamesCategory.MMOFPS.value -> GamesCategory.MMOFPS
                GamesCategory.MMOTPS.value -> GamesCategory.MMOTPS
                GamesCategory._3D.value -> GamesCategory._3D
                GamesCategory._2D.value -> GamesCategory._2D
                GamesCategory.ANIME.value -> GamesCategory.ANIME
                GamesCategory.FANTASY.value -> GamesCategory.FANTASY
                GamesCategory.SCI_FI.value -> GamesCategory.SCI_FI
                GamesCategory.FIGHTING.value -> GamesCategory.FIGHTING
                GamesCategory.ACTION_RPG.value -> GamesCategory.ACTION_RPG
                GamesCategory.ACTION.value -> GamesCategory.ACTION
                GamesCategory.MILITARY.value -> GamesCategory.MILITARY
                GamesCategory.MARTIAL_ARTS.value -> GamesCategory.MARTIAL_ARTS
                GamesCategory.FLIGHT.value -> GamesCategory.FLIGHT
                GamesCategory.LOW_SPEC.value -> GamesCategory.LOW_SPEC
                GamesCategory.TOWER_DEFENSE.value -> GamesCategory.TOWER_DEFENSE
                GamesCategory.HORROR.value -> GamesCategory.HORROR
                GamesCategory.MMORTS.value -> GamesCategory.MMORTS
                else -> GamesCategory.MMORPG
            }
        }
}