package com.dendi.android.gamessearchapp.presentation.core


import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.dendi.android.gamessearchapp.core.Communication
import com.dendi.android.gamessearchapp.core.Observe
import com.dendi.android.gamessearchapp.core.ResourceProvider

/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseViewModel<E : Communication<T>, T>(
    protected val communication: E,
    private val resourceProvider: ResourceProvider
) : ViewModel(), ScrollPosition, Observe<T> {

    override fun observe(owner: LifecycleOwner, observe: Observer<T>) =
        communication.observe(owner, observe)

    protected fun checkForInternet(): Boolean {
        val connectivityManager = resourceProvider.provideContext()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}

interface ScrollPosition {
    fun saveScrollPosition(position: Int) = Unit
    fun scrollPosition(): Int = 0
}