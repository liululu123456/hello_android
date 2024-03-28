package com.thoughtworks.androidtrain

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)
        println("LoginActivity:onCreate-------->")
    }

    override fun onStart() {
        super.onStart()
        println("LoginActivity:onStart-------->")
    }

    override fun onResume() {
        super.onResume()
        println("LoginActivity:onResume-------->")

    }

    override fun onStop() {
        super.onStop()
        println("LoginActivity:onStop-------->")

    }

    override fun onDestroy() {
        super.onDestroy()
        println("LoginActivity:onDestroy-------->")

    }

    override fun onRestart() {
        super.onRestart()
        println("LoginActivity:onRestart-------->")
    }

}