package com.dendi.android.gamessearchapp.data.core

import androidx.room.TypeConverter
import com.dendi.android.gamessearchapp.data.detail.cache.ScreenshotCache
import com.dendi.android.gamessearchapp.data.detail.cache.SystemRequirementsCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class Convertors {

    @TypeConverter
    fun fromScreenshot(screenshotDb: List<ScreenshotCache>): String {
        val type = object : TypeToken<List<ScreenshotCache>>() {}.type
        return Gson().toJson(screenshotDb, type)
    }

    @TypeConverter
    fun toScreenshot(json: String): List<ScreenshotCache> {
        val type = object : TypeToken<List<ScreenshotCache>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun fromSystemRequirements(requirements: SystemRequirementsCache): String {
        val type = object : TypeToken<SystemRequirementsCache>() {}.type
        return Gson().toJson(requirements, type)
    }

    @TypeConverter
    fun toSystemRequirements(json: String): SystemRequirementsCache {
        val type = object : TypeToken<SystemRequirementsCache>() {}.type
        return Gson().fromJson(json, type)
    }
}