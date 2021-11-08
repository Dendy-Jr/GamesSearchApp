package com.dendi.android.gamessearchapp.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * @author Dendy-Jr on 08.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Communication<T>: Abstract.Mapper {
    fun map(data: T)
    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    abstract class Base<T> : Communication<T> {
        private val liveData = MutableLiveData<T>()
        override fun map(data: T) {
            liveData.value = data!!
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) =
            liveData.observe(owner, observer)
    }
}