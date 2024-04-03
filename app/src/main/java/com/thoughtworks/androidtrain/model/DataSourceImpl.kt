package com.thoughtworks.androidtrain.model

import com.thoughtworks.androidtrain.model.dao.TweetDao
import com.thoughtworks.androidtrain.model.entity.Tweet
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable

class DataSourceImpl(private val tweetDao: TweetDao) : DataSource {
    override fun fetchTweets(): Flowable<List<Tweet>> {
        return Flowable.create({ emitter ->
            val tweets = tweetDao.getAllTweets()
            emitter.onNext(tweets)
            emitter.onComplete()
        }, BackpressureStrategy.BUFFER)
    }
}