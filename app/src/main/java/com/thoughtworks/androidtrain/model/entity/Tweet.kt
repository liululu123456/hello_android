package com.thoughtworks.androidtrain.model.entity
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tweet")
data class Tweet(
    @PrimaryKey val id: Long,
    var content: String?,
    var images: List<Image>?,
    var sender: Sender?,
    var comments: List<Comment>?,
    var error: String?,
    @SerializedName("unknown error")
    var unknownError: String?
)