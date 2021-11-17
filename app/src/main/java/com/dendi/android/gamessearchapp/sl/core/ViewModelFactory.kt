package com.dendi.android.gamessearchapp.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dendi.android.gamessearchapp.presentation.detail.DetailViewModel
import com.dendi.android.gamessearchapp.presentation.favorites.FavoritesViewModel
import com.dendi.android.gamessearchapp.presentation.games.GamesViewModel

/**
 * @author Dendy-Jr on 12.11.2021
 * olehvynnytskyi@gmail.com
 */
class ViewModelFactory(
    private val container: DependencyContainer,
) : ViewModelProvider.Factory {

    private val featuresByClass = HashMap<Class<*>, Feature>().apply {
        put(GamesViewModel::class.java, Feature.Games)
        put(DetailViewModel::class.java, Feature.Detail)
        put(FavoritesViewModel::class.java, Feature.Favorites)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val feature = featuresByClass[modelClass]
            ?: throw IllegalStateException("Feature by class ${modelClass::class.java} not found")
        return container.module(feature).viewModel() as T
    }
}