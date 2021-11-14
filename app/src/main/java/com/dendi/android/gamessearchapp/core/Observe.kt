package com.dendi.android.gamessearchapp.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

/**
 * @author Dendy-Jr on 11.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Observe<T> {
    fun observe(owner: LifecycleOwner, observe: Observer<T>)
}