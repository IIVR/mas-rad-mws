package com.example.datacheck

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_breached_account.*
import java.util.*


class BreachedAccountActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var breachList: ArrayList<DataBreach>
    lateinit var adapter: BreachListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breached_account)

        breachList = intent.getSerializableExtra("breachList") as ArrayList<DataBreach>

        adapter = BreachListAdapter(breachList, this)

        val recyclerView = findViewById<RecyclerView>(R.id.breach_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    override fun onClick(v: View) {
        if (v.tag != null){
            println("")
        }
    }

}