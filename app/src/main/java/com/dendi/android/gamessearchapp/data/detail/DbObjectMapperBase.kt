package com.dendi.android.gamessearchapp.data.detail

import com.dendi.android.gamessearchapp.data.detail.cache.DetailDb

/**
 * @author Dendy-Jr on 05.11.2021
 * olehvynnytskyi@gmail.com
 */
class DbObjectMapperBase : DbObject<DetailData, DetailDb> {
    override fun map(data: DetailData) = data.map(data)
}