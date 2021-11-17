package com.dendi.android.gamessearchapp.core


/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Abstract {

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface DataObject

    interface CacheObject

    interface CloudObject

    interface Mapper {

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToDomain<S, R> : Data<S, R> {
            fun map(e: Exception): R
        }

        interface DomainToUi<S, T> : Data<S, T> {
            fun map(errorType: ErrorType): T
        }

        class Empty : Mapper
    }

    interface ToGameMapper<T> : Mapper {
        fun map(id: Int, thumbnail: String, title: String, genre: String, releaseDate: String): T
    }

    interface SystemRequirementsMapper<T> : Mapper {
        fun map(
            id: Int,
            graphics: String,
            memory: String,
            os: String,
            processor: String,
            storage: String,
        ): T
    }

    interface ScreenshotMapper<T> : Mapper {
        fun map(id: Int, image: String): T
    }

    interface FavoriteMapper<T> : Mapper {
        fun map(id: Int, thumbnail: String, title: String, platform: String, developer: String): T
    }
}