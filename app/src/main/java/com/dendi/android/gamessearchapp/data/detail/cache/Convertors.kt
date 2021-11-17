package com.dendi.android.gamessearchapp.data.detail.cache

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class Convertors {

    @TypeConverter
    fun fromScreenshot(screenshotDb: List<ScreenshotCache.Base>): String {
        val type = object : TypeToken<List<ScreenshotCache.Base>>() {}.type
        return Gson().toJson(screenshotDb, type)
    }

    @TypeConverter
    fun toScreenshot(json: String): List<ScreenshotCache.Base> {
        val type = object : TypeToken<List<ScreenshotCache.Base>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun fromSystemRequirements(requirements: SystemRequirementsCache.Base): String {
        val type = object : TypeToken<SystemRequirementsCache.Base>() {}.type
        return Gson().toJson(requirements, type)
    }

    @TypeConverter
    fun toSystemRequirements(json: String): SystemRequirementsCache.Base {
        val type = object : TypeToken<SystemRequirementsCache.Base>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun fromDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun toDate(date: Date?): Long? {
        return date?.time
    }

}