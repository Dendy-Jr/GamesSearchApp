package com.dendi.android.gamessearchapp.core

import com.dendi.android.gamessearchapp.data.favorites.FavoritesScrollPositionCache
import com.dendi.android.gamessearchapp.data.games.GamesScrollPositionCache

/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ScrollPositionCache : GamesScrollPositionCache, FavoritesScrollPositionCache {

    abstract class Abstract(provide: PreferenceProvide) : ScrollPositionCache {

        private val sharedPreferences by lazy { provide.provideSharedPreferences(fileName()) }

        override fun saveGamesScrollPosition(position: Int) = save(GAMES, position)

        override fun saveFavoritesScrollPosition(position: Int) = save(FAVORITES, position)


        override fun gamesScrollPosition() = get(GAMES)
        override fun favoritesScrollPosition() = get(FAVORITES)


        protected abstract fun fileName(): String
        protected abstract fun keySuffix(): String

        private fun save(featureName: String, position: Int) =
            sharedPreferences.edit().putInt(featureName + keySuffix(), position).apply()

        private fun get(featureName: String) =
            sharedPreferences.getInt(featureName + keySuffix(), 0)


        private companion object {
            const val GAMES = "GAMES"
            const val FAVORITES = "FAVORITES"
        }
    }

    class Base(provide: PreferenceProvide) : Abstract(provide) {
        override fun fileName() = SCROLL_POSITION_FILE_NAME
        override fun keySuffix() = SCROLL_POSITION_KEY_SUFFIX

        private companion object {
            const val SCROLL_POSITION_FILE_NAME = "scrollPosition"
            const val SCROLL_POSITION_KEY_SUFFIX = "_scrollPosition"
        }
    }
}