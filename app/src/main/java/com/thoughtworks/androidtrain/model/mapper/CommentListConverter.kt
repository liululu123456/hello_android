package com.thoughtworks.androidtrain.model.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.thoughtworks.androidtrain.model.entity.Comment

class CommentListConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromComments(comments: List<Comment>): String {
        return gson.toJson(comments)
    }

    @TypeConverter
    fun toComments(commentsJson: String): List<Comment> {
        val type = object : TypeToken<List<Comment>>() {}.type
        return gson.fromJson(commentsJson, type)
    }
}