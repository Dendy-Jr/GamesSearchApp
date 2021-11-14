package com.dendi.android.gamessearchapp.sl.core

/**
 * @author Dendy-Jr on 12.11.2021
 * olehvynnytskyi@gmail.com
 */
sealed class Feature {

    object Games: Feature()

    object Detail: Feature()
}