package com.dendi.android.gamessearchapp.sl.core

import androidx.lifecycle.ViewModel

/**
 * @author Dendy-Jr on 12.11.2021
 * olehvynnytskyi@gmail.com
 */
interface BaseModule<T : ViewModel> {

    fun viewModel(): T
}