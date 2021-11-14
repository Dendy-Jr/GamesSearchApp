package com.dendi.android.gamessearchapp.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

/**
 * @author Dendy-Jr on 08.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Communication<T> : Observe<T>, Abstract.Mapper.Data<T, Unit> {

    abstract class Base<T : Any> : Communication<T> {
        private val liveData = MutableLiveData<T>()
        override fun map(data: T) {
            liveData.value = data
        }

        override fun observe(owner: LifecycleOwner, observe: Observer<T>) =
            liveData.observe(owner, observe)
    }
}