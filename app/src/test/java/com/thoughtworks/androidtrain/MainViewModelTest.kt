package com.thoughtworks.androidtrain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.thoughtworks.androidtrain.model.dataSource.DataSource
import com.thoughtworks.androidtrain.model.entity.Tweet
import com.thoughtworks.androidtrain.viewModel.MainViewModel
import io.reactivex.Flowable
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MainViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockDataSource: DataSource

    private lateinit var viewModel: MainViewModel

    @Mock
    private lateinit var mockObserver: Observer<List<Tweet>>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(mockDataSource)
        viewModel.tweetsLiveData.observeForever(mockObserver)
    }


    @Test
    fun fetchTweetsDataSortedByDate() {
        val tweet1 = Tweet(1, "Content 1", "2024040901", null, null, null, null, null)
        val tweet2 = Tweet(2, "Content 2", "2024040902", null, null, null, null, null)
        val tweet3 = Tweet(3, "Content 3", "2024040903", null, null, null, null, null)

        val mockTweets = listOf(tweet1,tweet2,tweet3)
        val sortedTweet = listOf(tweet3,tweet2,tweet1)

        Mockito.`when`(runBlocking { mockDataSource.fetchTweets() })
            .thenReturn(Flowable.fromArray(mockTweets))

        runBlocking {
            viewModel.fetchTweetsData()
        }
        verify(mockObserver).onChanged(sortedTweet)
    }
}