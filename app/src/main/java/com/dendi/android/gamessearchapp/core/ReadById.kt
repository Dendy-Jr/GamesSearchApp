package com.dendi.android.gamessearchapp.core

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ReadById<T> {
    suspend fun readId(id: Int): T
}