package com.thoughtworks.androidtrain.model

import com.thoughtworks.androidtrain.model.entity.Tweet
import io.reactivex.Flowable

interface DataSource {
    suspend fun fetchTweets(): Flowable<List<Tweet>>

}