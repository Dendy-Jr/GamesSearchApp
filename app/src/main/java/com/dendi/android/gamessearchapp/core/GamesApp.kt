package com.dendi.android.gamessearchapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.viewbinding.BuildConfig
import com.dendi.android.gamessearchapp.sl.core.CoreModule
import com.dendi.android.gamessearchapp.sl.core.DependencyContainer
import com.dendi.android.gamessearchapp.sl.core.ViewModelFactory
import timber.log.Timber

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
class GamesApp : Application() {

    private val coreModule = CoreModule()

    private val factory by lazy {
        ViewModelFactory(DependencyContainer.Base(coreModule))
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        coreModule.init(this)
    }

    fun <T : ViewModel> viewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory)[modelClass]
}