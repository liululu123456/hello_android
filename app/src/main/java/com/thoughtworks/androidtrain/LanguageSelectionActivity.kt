package com.thoughtworks.androidtrain

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.thoughtworks.androidtrain.fragment.AndroidTextFragment
import com.thoughtworks.androidtrain.fragment.JavaTextFragment

class LanguageSelectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language_selection_layout)

        val androidButton = findViewById<Button>(R.id.android_button)
        val javaButton = findViewById<Button>(R.id.java_button)
        val fragment = AndroidTextFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

        androidButton.setOnClickListener{
            val androidFragment = AndroidTextFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, androidFragment)
                .commit()
        }
        javaButton.setOnClickListener{
            val androidFragment = JavaTextFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, androidFragment)
                .commit()
        }


    }

}