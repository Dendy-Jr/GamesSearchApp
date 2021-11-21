package com.dendi.android.gamessearchapp.core

/**
 * @author Dendy-Jr on 19.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Matcher<T> {

    fun matches(arg: T): Boolean
}