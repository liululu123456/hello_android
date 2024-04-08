package com.thoughtworks.androidtrain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thoughtworks.androidtrain.model.DataSource
import com.thoughtworks.androidtrain.model.entity.Tweet

class MainViewModal(private val dataSource: DataSource): ViewModel() {
    val tweetsLiveData: MutableLiveData<List<Tweet>> = MutableLiveData()

    suspend fun fetchTweetsData(){
            val tweets = dataSource.fetchTweets().blockingFirst()
                .sortedByDescending { it.date}
                .filter { it.content != null }

            tweetsLiveData.postValue(tweets)
    }


}