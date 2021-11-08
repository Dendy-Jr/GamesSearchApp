package com.dendi.android.gamessearchapp.presentation.core

import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {
    fun <T : Parcelable> publishResult(result: T)

    fun <T : Parcelable> listenerResult(
        clazz: Class<T>,
        owner: LifecycleOwner,
        listener: (T) -> Unit,
    )

    fun backToHome()

    fun back()
}

//todo use it in the future