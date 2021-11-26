package com.dendi.android.gamessearchapp.core

/**
 * @author Dendy-Jr on 09.11.2021
 * olehvynnytskyi@gmail.com
 */
interface ListMapper<T> : Abstract.Mapper.Data<List<T>, Unit> {
    class Empty<T> : ListMapper<T> {
        override fun map(data: List<T>) = Unit
    }
}

