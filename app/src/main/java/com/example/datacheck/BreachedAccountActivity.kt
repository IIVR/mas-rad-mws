package com.example.datacheck

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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