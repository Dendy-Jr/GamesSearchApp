package com.dendi.android.gamessearchapp.presentation.detail

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dendi.android.gamessearchapp.core.Abstract
import com.dendi.android.gamessearchapp.domain.detail.DetailInteractor
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
    private val mapper: Abstract.HandlerUiMapper<DetailHandlerUi>,
) : ViewModel() {

    fun fetchDetail(id: Int) {
        detailCommunication.map(DetailUi.Progress)
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = detailInteractor.fetchDetail(id)
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                resultUi.map(detailCommunication)
            }
        }
    }

    fun observeDetail(owner: LifecycleOwner, observer: Observer<DetailUi>) {
        detailCommunication.observe(owner, observer)
    }
}