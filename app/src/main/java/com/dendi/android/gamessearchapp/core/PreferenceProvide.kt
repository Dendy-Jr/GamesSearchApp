package com.dendi.android.gamessearchapp.core

import android.content.SharedPreferences

/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
interface PreferenceProvide {

    fun provideSharedPreferences(name: String): SharedPreferences
}