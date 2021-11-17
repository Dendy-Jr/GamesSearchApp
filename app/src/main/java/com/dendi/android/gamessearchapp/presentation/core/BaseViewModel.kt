package com.dendi.android.gamessearchapp.presentation.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.dendi.android.gamessearchapp.core.Communication
import com.dendi.android.gamessearchapp.core.Observe

/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
abstract class BaseViewModel<E : Communication<T>, T>(
    protected val communication: E,
) : ViewModel(), ScrollPosition, Observe<T> {

    override fun observe(owner: LifecycleOwner, observe: Observer<T>) =
        communication.observe(owner, observe)
}

interface ScrollPosition {
    fun saveScrollPosition(position: Int) = Unit
    fun scrollPosition(): Int = 0
}