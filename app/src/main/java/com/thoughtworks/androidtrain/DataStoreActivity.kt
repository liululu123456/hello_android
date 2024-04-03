package com.thoughtworks.androidtrain

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "db")
class DataStoreActivity : AppCompatActivity() {
    val key = booleanPreferencesKey("isHintShown")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val isHintShown = this.dataStore.data.map { pre -> pre[key]?:false }
        lifecycleScope.launch {
            if (isHintShown.first()) {
                setContentView(R.layout.activity_data_store_right)
            } else {
                setContentView(R.layout.activity_data_store_left)
                val btnGotIt: Button = findViewById(R.id.btnGotIt)
                btnGotIt.setOnClickListener {
                    setContentView(R.layout.activity_data_store_right)
                    lifecycleScope.launch { updateHintVisible() }
                }
            }
        }

    }


    private suspend fun updateHintVisible() {
        this.dataStore.edit { data -> data[key] = false }
    }

}
