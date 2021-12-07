package com.dendi.android.gamessearchapp.presentation.detail

import com.dendi.android.gamessearchapp.core.Abstract

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */

data class DetailUiState(
    private val detail: DetailUi,
) : Abstract.Mapper.DetailUiStateObject {
    override fun map(mapper: DetailCommunication) = mapper.map(detail)
}
