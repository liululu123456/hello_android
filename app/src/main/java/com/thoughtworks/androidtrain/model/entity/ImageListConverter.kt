package com.thoughtworks.androidtrain.model.entity

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.androidtrain.model.entity.Image

class ImageListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromImageList(images: List<Image>): String {
        return gson.toJson(images)
    }

    @TypeConverter
    fun toImageList(imagesJson: String): List<Image> {
        val type = object : TypeToken<List<Image>>() {}.type
        return gson.fromJson(imagesJson, type)
    }
}