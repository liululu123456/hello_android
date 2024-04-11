package com.thoughtworks.androidtrain.ui.activity

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.thoughtworks.androidtrain.model.dataBase.AppDatabase
import com.thoughtworks.androidtrain.model.dataSource.DataSourceImpl
import com.thoughtworks.androidtrain.ui.compose.TweetsScreen
import com.thoughtworks.androidtrain.viewModel.MainViewModel
import com.thoughtworks.androidtrain.viewModel.MainViewModelFactory

class ComposeTweetsActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataSource = initDataSource()
        val viewModal = initViewModal(dataSource)
        setContent {
            TweetsScreen(viewModal)
        }
    }

    private fun initDataSource(): DataSourceImpl {
        val appDatabase = Room.databaseBuilder(this, AppDatabase::class.java, "app_database").build()
        val tweetDao = appDatabase.tweetDao()
        return DataSourceImpl(this@ComposeTweetsActivity,tweetDao)

    }
    private fun initViewModal( dataSource : DataSourceImpl): MainViewModel {
        val viewModelFactory = MainViewModelFactory(dataSource)
        return ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
    }

}