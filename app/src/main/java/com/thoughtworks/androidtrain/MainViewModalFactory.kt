package com.thoughtworks.androidtrain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thoughtworks.androidtrain.model.DataSource

class MainViewModalFactory(private val dataSource: DataSource) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModal::class.java)) {
            return MainViewModal(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}