package com.dendi.android.gamessearchapp.core

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringRes

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ResourceProvider : PreferenceProvide {

    fun getString(@StringRes id: Int): String

    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int) = context.getString(id)


        override fun provideSharedPreferences(name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)
    }
}