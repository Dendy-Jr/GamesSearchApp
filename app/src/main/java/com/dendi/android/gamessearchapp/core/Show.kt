package com.dendi.android.gamessearchapp.core

/**
 * @author Dendy-Jr on 23.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Show<T> {
   suspend fun show(): T
}