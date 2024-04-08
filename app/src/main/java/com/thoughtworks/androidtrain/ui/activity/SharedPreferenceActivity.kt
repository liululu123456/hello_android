package com.thoughtworks.androidtrain.ui.activity

import android.content.Context
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.androidtrain.R

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isHintShown = sharedPreferences.getBoolean("isHintShown", false)


        if (isHintShown) {
            setContentView(R.layout.activity_shared_preference_right)
        } else {
            setContentView(R.layout.activity_shared_preference_left)
            val btnGotIt: Button = findViewById(R.id.btnGotIt)
            btnGotIt.setOnClickListener {
                setContentView(R.layout.activity_shared_preference_right)
                with (sharedPreferences.edit()) {
                    putBoolean("isHintShown", true)
                    apply()
                }
            }
        }

    }

}