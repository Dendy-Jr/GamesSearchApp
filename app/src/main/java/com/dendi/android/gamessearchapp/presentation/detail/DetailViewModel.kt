package com.dendi.android.gamessearchapp.presentation.detail


import androidx.lifecycle.viewModelScope
import com.dendi.android.gamessearchapp.domain.detail.DetailDomainStateToUiMapper
import com.dendi.android.gamessearchapp.domain.detail.DetailInteractor
import com.dendi.android.gamessearchapp.presentation.core.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
class DetailViewModel(
    private val detailInteractor: DetailInteractor,
    private val detailCommunication: DetailCommunication,
    private val mapper: DetailDomainStateToUiMapper<DetailUiState>,
) : BaseViewModel<DetailCommunication, DetailUi>(detailCommunication) {

    fun fetchDetail(id: Int) {
        detailCommunication.map(DetailUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = detailInteractor.readId(id)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(detailCommunication)
            }
        }
    }
}