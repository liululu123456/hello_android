package com.thoughtworks.androidtrain.ui.compose

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.unit.dp
import com.thoughtworks.androidtrain.viewModel.MainViewModel

@Composable
fun TweetsScreen(viewModel: MainViewModel)  {
    val tweets by viewModel.tweetsLiveData.observeAsState()
    LaunchedEffect(Unit) {
         viewModel.fetchTweetsData()
    }
    tweets?.let { tweetList ->
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
        ) {
            items(tweetList) { tweet ->
                TweetItem(tweet
                ) { comment -> viewModel.updateComment(tweet.id, comment) }
            }
            item {
                Text("到底了")
            }
        }
    }
}