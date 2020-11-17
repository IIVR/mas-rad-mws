package com.example.datacheck

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun goToEmail(view: View) {
        println("Go to email check")
    }
    fun goToPasswordActivity(view: View) {
        val intent = Intent(this@MainActivity, PasswordActivity::class.java)
        startActivity(intent)
    }
    fun goToAbout(view: View) {
        println("Go to about")
    }
}