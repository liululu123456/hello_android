package com.thoughtworks.androidtrain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class TweetsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
    }
}

