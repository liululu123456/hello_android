package com.thoughtworks.androidtrain.model
import com.google.gson.annotations.SerializedName

data class Tweet(
    var content: String?,
    var images: List<Image>?,
    var sender: Sender?,
    var comments: List<Comment>?,
    var error: String?,
    @SerializedName("unknown error")
    var unknownError: String?
)