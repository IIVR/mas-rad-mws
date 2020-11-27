package com.example.datacheck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.ArrayList

class BreachSiteDetailsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breach_site_details)

        var breach = intent.getParcelableExtra("breach") as DataBreach?

        println("N "+breach?.name)

    }
}