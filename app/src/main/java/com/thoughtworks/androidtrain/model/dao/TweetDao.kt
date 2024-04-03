package com.thoughtworks.androidtrain.model.dao

import androidx.room.Query
import com.thoughtworks.androidtrain.model.entity.Tweet

interface TweetDao {
    @Query("SELECT * FROM Tweet")
    fun getAllTweets(): List<Tweet>
}