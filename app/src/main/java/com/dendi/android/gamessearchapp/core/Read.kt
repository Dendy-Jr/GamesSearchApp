package com.dendi.android.gamessearchapp.core

/**
 * @author Dendy-Jr on 10.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Read<E, T> {
    suspend fun read(data: E): T
}