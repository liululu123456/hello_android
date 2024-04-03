package com.thoughtworks.androidtrain.model.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.thoughtworks.androidtrain.model.entity.Tweet
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TweetDao {
    @Query("SELECT * FROM Tweet")
    fun getAllTweets(): Flowable<List<Tweet>>
    @Update
    fun updateTweets(tweets: List<Tweet>): Completable
}