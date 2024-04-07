package com.thoughtworks.androidtrain.model.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.thoughtworks.androidtrain.model.entity.Sender

class SenderConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromSender(sender: Sender): String {
        return gson.toJson(sender)
    }

    @TypeConverter
    fun toSender(senderJson: String): Sender {
        return gson.fromJson(senderJson, Sender::class.java)
    }
}