package com.dendi.android.gamessearchapp.core

import android.content.Context
import androidx.annotation.StringRes

/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ResourceProvider {

    fun getString(@StringRes id: Int): String

    class Base(private val context: Context) : ResourceProvider {
        override fun getString(id: Int): String {
            return context.getString(id)
        }
    }
}