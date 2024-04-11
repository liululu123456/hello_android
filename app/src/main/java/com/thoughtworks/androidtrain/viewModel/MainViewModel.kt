package com.thoughtworks.androidtrain.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thoughtworks.androidtrain.model.dataSource.DataSource
import com.thoughtworks.androidtrain.model.entity.Tweet

class MainViewModel(private val dataSource: DataSource): ViewModel() {
    private val tweetStates = mutableStateListOf<Tweet>()

    val tweetsLiveData: MutableLiveData<List<Tweet>> = MutableLiveData()

    suspend fun fetchTweetsData(){
            val tweets = dataSource.fetchTweets().blockingFirst()
                .sortedByDescending { it.date}
                .filter { it.content != null }

            tweetsLiveData.postValue(tweets)
        tweetStates.clear()
        tweetStates.addAll(tweets)
    }

    fun updateComment(tweetId: Long, comment: String) {
        println(tweetId)
        val tweet = tweetStates.find { it.id == tweetId }
        tweet?.comments?.get(0)?.content = comment
    }
}