package com.dendi.android.gamessearchapp.core

import android.content.SharedPreferences
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
interface PreferenceProvide {

    fun provideSharedPreferences(name: String): SharedPreferences

    fun provideDataStore(name: String): DataStore<Preferences>
}