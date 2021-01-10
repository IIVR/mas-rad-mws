package com.example.datacheck.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.datacheck.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }

    fun goToEmailActivity(view: View) {
        val intent = Intent(this@MainActivity, EmailActivity::class.java)
        startActivity(intent)
    }
    fun goToPasswordActivity(view: View) {
        val intent = Intent(this@MainActivity, PasswordActivity::class.java)
        startActivity(intent)
    }
    fun goToBreachSiteActivity(view: View) {
        val intent = Intent(this@MainActivity, BreachSiteActivity::class.java)
        startActivity(intent)
    }
}