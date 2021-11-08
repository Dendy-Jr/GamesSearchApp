package com.dendi.android.gamessearchapp.data.detail.cache

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * @author Dendy-Jr on 02.11.2021
 * olehvynnytskyi@gmail.com
 */
class Convertors {

    @TypeConverter
    fun fromScreenshot(screenshotDb: List<ScreenshotDb>): String {
        val type = object : TypeToken<List<ScreenshotDb>>() {}.type
        return Gson().toJson(screenshotDb, type)
    }

    @TypeConverter
    fun toScreenshot(json: String): List<ScreenshotDb> {
        val type = object : TypeToken<List<ScreenshotDb>>() {}.type
        return Gson().fromJson(json, type)
    }

    @TypeConverter
    fun fromSystemRequirements(requirements: SystemRequirementsDb): String {
        val type = object : TypeToken<SystemRequirementsDb>() {}.type
        return Gson().toJson(requirements, type)
    }

    @TypeConverter
    fun toSystemRequirements(json: String): SystemRequirementsDb {
        val type = object : TypeToken<SystemRequirementsDb>() {}.type
        return Gson().fromJson(json, type)
    }
}