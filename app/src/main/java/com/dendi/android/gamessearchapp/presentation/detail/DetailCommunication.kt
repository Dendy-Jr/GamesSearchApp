package com.dendi.android.gamessearchapp.presentation.detail


import com.dendi.android.gamessearchapp.core.Communication

/**
 * @author Dendy-Jr on 03.11.2021
 * olehvynnytskyi@gmail.com
 */
interface DetailCommunication : Communication<DetailUi> {

    class Base : Communication.Base<DetailUi>(), DetailCommunication
}