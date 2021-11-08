package com.dendi.android.gamessearchapp.core

import com.dendi.android.gamessearchapp.data.detail.DetailData
import com.dendi.android.gamessearchapp.data.detail.ScreenshotData
import com.dendi.android.gamessearchapp.data.detail.SystemRequirementsData
import com.dendi.android.gamessearchapp.data.games.GameData
import com.dendi.android.gamessearchapp.domain.detail.DetailDomain
import com.dendi.android.gamessearchapp.domain.detail.DetailHandlerDomain
import com.dendi.android.gamessearchapp.domain.detail.ScreenshotDomain
import com.dendi.android.gamessearchapp.domain.detail.SystemRequirementsDomain
import com.dendi.android.gamessearchapp.domain.games.ErrorType
import com.dendi.android.gamessearchapp.domain.games.GameDomain
import com.dendi.android.gamessearchapp.domain.games.GamesDomain
import com.dendi.android.gamessearchapp.presentation.detail.*
import com.dendi.android.gamessearchapp.presentation.games.GameUi
import com.dendi.android.gamessearchapp.presentation.games.GamesUi


/**
 * @author Dendy-Jr on 01.11.2021
 * olehvynnytskyi@gmail.com
 */
interface Abstract {

    interface Object<T, M : Mapper> {
        fun map(mapper: M): T

        interface Data<T, M : Mapper> : Object<T, M> {
            interface GameObject : Data<GameData, GameMapper<GameData>>
            interface DetailObject : Data<DetailData, DetailDataMapper<DetailData>>
            interface SystemRequirementsObject :
                Data<SystemRequirementsData, SystemRequirementsMapper<SystemRequirementsData>>

            interface ScreenshotObject : Data<ScreenshotData, ScreenshotMapper<ScreenshotData>>
        }

        interface Cache {
            interface GameObject<T> : Domain.GameObject {
                fun map(mapper: GameMapper<T>): T
            }

            interface SystemRequirementsObject<T> : Domain.SystemRequirementsObject {
                fun map(mapper: SystemRequirementsMapper<T>): T
            }

            interface ScreenshotObject<T> : Domain.ScreenshotObject {
                fun map(mapper: ScreenshotMapper<T>): T
            }
        }

        interface Domain<T, M : Mapper> : Object<T, M> {
            interface GameObject : Domain<GameDomain, GameMapper<GameDomain>>
            interface GamesObject : Domain<GamesDomain, GamesDomainMapper<GamesDomain>>
            interface DetailObject : Domain<DetailDomain, DetailDomainMapper<DetailDomain>>
            interface HandlerObject :
                Domain<DetailHandlerDomain, HandlerDomainMapper<DetailHandlerDomain>>

            interface SystemRequirementsObject :
                Domain<SystemRequirementsDomain, SystemRequirementsMapper<SystemRequirementsDomain>>

            interface ScreenshotObject :
                Domain<ScreenshotDomain, ScreenshotMapper<ScreenshotDomain>>
        }

        interface Ui<T, M : Mapper> : Object<T, M> {
            interface GameObject : Ui<GameUi, GameMapper<GameUi>>
            interface GamesObject : Ui<GamesUi, GamesUiMapper<GamesUi>>
            interface DetailObject : Ui<DetailUi, DetailUiMapper<DetailUi>>
            interface HandlerObject : Ui<DetailHandlerUi, HandlerUiMapper<DetailHandlerUi>>
            interface SystemRequirementsObject :
                Ui<SystemRequirementsUi, SystemRequirementsMapper<SystemRequirementsUi>>

            interface ScreenshotObject : Ui<ScreenshotUi, ScreenshotMapper<ScreenshotUi>>
        }

        interface UnitObject<M : Mapper> {
            fun map(mapper: M)
        }
    }

    interface Mapper {
        class Empty : Mapper
    }

    interface GameMapper<T> : Mapper {
        fun map(id: Int, thumbnail: String, title: String): T
    }

    interface GamesDomainMapper<T> : Mapper {
        fun map(games: List<GameData>): T
        fun map(exception: Exception): T

    }

    interface GamesUiMapper<T> : Mapper {
        fun map(games: List<GameDomain>): T
        fun map(errorType: ErrorType): T
    }

    interface AdapterGameMapper : Mapper {
        fun map(id: Int, thumbnail: String, title: String)
        fun map(message: String)
    }

    interface AdapterDetailMapper : Mapper {
        fun map(
            description: String,
            developer: String,
            freetogameProfileUrl: String,
            gameUrl: String,
            genre: String,
            id: Int,
            systemRequirements: SystemRequirementsUi,
            platform: String,
            publisher: String,
            releaseDate: String,
            screenshots: List<ScreenshotUi>,
            shortDescription: String,
            status: String,
            thumbnail: String,
            title: String,
        )

        fun map(message: String)
    }

    interface DetailDataMapper<T> : Mapper {
        fun map(
            description: String,
            developer: String,
            freetogameProfileUrl: String,
            gameUrl: String,
            genre: String,
            id: Int,
            systemRequirements: SystemRequirementsData?,
            platform: String,
            publisher: String,
            releaseDate: String,
            screenshots: List<ScreenshotData>?,
            shortDescription: String,
            status: String,
            thumbnail: String,
            title: String,
        ): T
    }

    interface DetailDomainMapper<T> : Mapper {
        fun map(
            description: String,
            developer: String,
            freetogameProfileUrl: String,
            gameUrl: String,
            genre: String,
            id: Int,
            systemRequirements: SystemRequirementsDomain,
            platform: String,
            publisher: String,
            releaseDate: String,
            screenshots: List<ScreenshotDomain>,
            shortDescription: String,
            status: String,
            thumbnail: String,
            title: String,
        ): T
    }

    interface DetailUiMapper<T> : Mapper {
        fun map(
            description: String,
            developer: String,
            freetogameProfileUrl: String,
            gameUrl: String,
            genre: String,
            id: Int,
            systemRequirements: SystemRequirementsUi,
            platform: String,
            publisher: String,
            releaseDate: String,
            screenshots: List<ScreenshotUi>,
            shortDescription: String,
            status: String,
            thumbnail: String,
            title: String,
        ): T
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

    interface HandlerDomainMapper<T> : Mapper {
        fun map(detail: DetailData): T
        fun map(exception: Exception): T
    }

    interface HandlerUiMapper<T> : Mapper {
        fun map(detail: DetailDomain): T
        fun map(errorType: ErrorType): T
    }
}