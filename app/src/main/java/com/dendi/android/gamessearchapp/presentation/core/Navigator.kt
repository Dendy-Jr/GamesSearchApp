package com.dendi.android.gamessearchapp.presentation.core

import androidx.fragment.app.Fragment

/**
 * @author Dendy-Jr on 04.11.2021
 * olehvynnytskyi@gmail.com
 */

fun Fragment.navigator(): Navigator = requireActivity() as Navigator

interface Navigator {

    fun backToHome()

    fun startFragment(fragment: Fragment)

    fun launchFragment(fragment: Fragment)

    fun back()
}
