package com.dendi.android.gamessearchapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface GamesCommunication : Abstract.Mapper {

    fun map(games: List<GameUi>)
    fun observe(owner: LifecycleOwner, observer: Observer<List<GameUi>>)

    class Base : GamesCommunication {
        private val listLiveData = MutableLiveData<List<GameUi>>()

        override fun map(games: List<GameUi>) {
            listLiveData.value = games
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<GameUi>>) {
            listLiveData.observe(owner, observer)
        }
    }
}