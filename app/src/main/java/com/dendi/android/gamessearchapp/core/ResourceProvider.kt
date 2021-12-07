package com.dendi.android.gamessearchapp.core

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ResourceProvider : PreferenceProvide {

    fun getString(@StringRes id: Int): String

    class Base(private val context: Context) : ResourceProvider {

        private val Context.dataStore by preferencesDataStore(PREFERENCES_NAME)

        override fun getString(id: Int) = context.getString(id)

        override fun provideSharedPreferences(name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)

        override fun provideDataStore(name: String): DataStore<Preferences> {
            return context.dataStore
        }

        override fun getSystemService() =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        private companion object {
            const val PREFERENCES_NAME = "PREFERENCES_NAME"
        }
    }
}