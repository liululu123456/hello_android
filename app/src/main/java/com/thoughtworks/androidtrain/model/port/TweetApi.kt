package com.thoughtworks.androidtrain.model.port

import com.thoughtworks.androidtrain.model.entity.Tweet
import retrofit2.Response
import retrofit2.http.GET

interface TweetApi {
        @GET("tweets.json")
        suspend fun getTweets(): Response<List<Tweet>>
}