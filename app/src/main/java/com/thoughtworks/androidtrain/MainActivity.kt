package com.thoughtworks.androidtrain

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        addButtons()
    }

    private fun addButtons(){
        val linearlayout = findViewById<LinearLayout>(R.id.main)
        for (i in 1..20) {
            val button = Button(this)

            val buttonStringId = resources.getIdentifier("button$i", "string", packageName)
            val buttonBackgroundColor = ContextCompat.getColor(this,R.color.gray)
            button.text = resources.getString(buttonStringId)
            button.setBackgroundColor(buttonBackgroundColor)

            val layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            layoutParams.topMargin = 16
            button.layoutParams = layoutParams
            linearlayout.addView(button)

        }

    }
}