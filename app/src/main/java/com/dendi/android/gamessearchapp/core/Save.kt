package com.dendi.android.gamessearchapp.core

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Save<T> {
    suspend fun save(data: T)
}