package com.dendi.android.gamessearchapp.sl.detail

import com.dendi.android.gamessearchapp.data.detail.BaseDetailRepository
import com.dendi.android.gamessearchapp.data.detail.DetailDataMapper
import com.dendi.android.gamessearchapp.data.detail.DetailDataToDomainMapper
import com.dendi.android.gamessearchapp.data.detail.cache.DetailCacheDataSource
import com.dendi.android.gamessearchapp.data.detail.cache.DetailDataToCacheMapper
import com.dendi.android.gamessearchapp.data.detail.cloud.DetailCloudDataSource
import com.dendi.android.gamessearchapp.data.detail.cloud.DetailService
import com.dendi.android.gamessearchapp.data.favorites.BaseFavoriteCacheMapper
import com.dendi.android.gamessearchapp.domain.detail.*
import com.dendi.android.gamessearchapp.domain.favorites.BaseFavoriteDomainMapper
import com.dendi.android.gamessearchapp.presentation.detail.BaseDetailDomainStateToUiMapper
import com.dendi.android.gamessearchapp.presentation.detail.DetailCommunication
import com.dendi.android.gamessearchapp.presentation.detail.DetailDomainToUiMapperBase
import com.dendi.android.gamessearchapp.presentation.detail.DetailViewModel
import com.dendi.android.gamessearchapp.sl.core.BaseModule
import com.dendi.android.gamessearchapp.sl.core.CoreModule

/**
 * @author Dendy-Jr on 12.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailModule(private val coreModule: CoreModule) : BaseModule<DetailViewModel> {

    override fun viewModel(): DetailViewModel {

        val detailInteractor = DetailInteractor.Base(
            BaseDetailRepository(
                DetailCloudDataSource.Base(
                    coreModule.networkService(DetailService::class.java)
                ),
                DetailCacheDataSource.Base(
                    coreModule.detailDao,
                    coreModule.favoriteDao,
                    DetailDataToCacheMapper.Base()
                ),
                DetailDataMapper.Base()
            ),
            BaseDetailDataToDomainMapper(
                DetailDataToDomainMapper.Base()
            ),
            BaseFavoriteCacheMapper()
        )

        val communication = DetailCommunication.Base()

        return DetailViewModel(
            detailInteractor,
            communication,
            BaseDetailDomainStateToUiMapper(
                coreModule.resourceProvider,
                DetailDomainToUiMapperBase()
            ),
            BaseFavoriteDomainMapper()
        )
    }
}