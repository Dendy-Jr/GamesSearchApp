package com.dendi.android.gamessearchapp.core

import com.dendi.android.gamessearchapp.data.detail.DetailDataMapper
import com.dendi.android.gamessearchapp.data.detail.DetailDataStateToDomainStateMapper
import com.dendi.android.gamessearchapp.data.detail.DetailDataToDomainMapper
import com.dendi.android.gamessearchapp.data.detail.cache.DetailCacheMapper
import com.dendi.android.gamessearchapp.domain.detail.DetailDomainStateToUiStateMapper
import com.dendi.android.gamessearchapp.domain.detail.DetailDomainToUiMapper
import com.dendi.android.gamessearchapp.domain.games.GamesDataStateToDomainStateMapper
import com.dendi.android.gamessearchapp.domain.games.GamesDomainStateToUiStateMapper
import com.dendi.android.gamessearchapp.presentation.detail.DetailCommunication
import com.dendi.android.gamessearchapp.presentation.detail.DetailUiMapper
import com.dendi.android.gamessearchapp.presentation.detail.ScreenshotUi
import com.dendi.android.gamessearchapp.presentation.games.GameUi
import com.dendi.android.gamessearchapp.presentation.games.GameUiMapper


/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Abstract {

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface Mapper {

        interface GamesObject {
            fun <T> map(mapper: GameMapper<T>): T
        }

        interface GamesUiObject {
            fun <T> map(mapper: GameUiMapper<T>): T
        }

        interface GamesUiStateObject {
            fun map(mapper: ListMapper<GameUi>)
        }

        interface GamesDataStateObject {
            fun <T> map(mapper: GamesDataStateToDomainStateMapper<T>): T
        }

        interface GamesDomainStateObject {
            fun <T> map(mapper: GamesDomainStateToUiStateMapper<T>): T
        }

        interface DetailCloudObject {
            fun <T> map(mapper: DetailCacheMapper<T>): T
        }

        interface DetailCacheObject {
            fun <T> map(mapper: DetailDataMapper<T>): T
        }

        interface DetailDataObject {
            fun <T> map(mapper: DetailDataToDomainMapper<T>): T
        }

        interface DataDetailStateObject {
            fun <T> map(mapper: DetailDataStateToDomainStateMapper<T>): T
        }

        interface DetailDomainStateObject {
            fun <T> map(mapper: DetailDomainStateToUiStateMapper<T>): T
        }

        interface DetailDomainObject {
            fun <T> map(mapper: DetailDomainToUiMapper<T>): T
        }

        interface DetailUiStateObject {
            fun map(mapper: DetailCommunication)
        }

        interface DetailUiObject {
            fun <T> map(mapper: DetailUiMapper<T>): T
        }

        interface FavoriteObject {
            fun <T> map(mapper: FavoriteMapper<T>): T
        }

        interface SystemRequirementsObject {
            fun <T> map(mapper: SystemRequirementsMapper<T>): T
        }

        interface ScreenshotObject {
            fun <T> map(mapper: ScreenshotMapper<T>): T
        }

        interface ScreenshotsUiObject {
            fun map(mapper: ListMapper<ScreenshotUi>)
        }

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

    interface GameMapper<T> : Mapper {
        fun map(id: Int, thumbnail: String, title: String, shortDescription: String): T
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
        fun map(id: Int, thumbnail: String, title: String): T
    }
}