package com.example.datacheck.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datacheck.presentation.adapter.BreachListAdapter
import com.example.datacheck.model.DataBreach
import com.example.datacheck.R
import java.util.*


class BreachedAccountActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var breachList: ArrayList<DataBreach>
    lateinit var adapter: BreachListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breached_account)

        breachList = intent.getSerializableExtra("breachList") as ArrayList<DataBreach>

        adapter = BreachListAdapter(breachList){ item: DataBreach, position: Int ->
            println("Clicked on item  ${item.title} at position $position")
            showDetails(item)
        }

        val recyclerView = findViewById<RecyclerView>(R.id.breach_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    fun showDetails(item: DataBreach) {

        val intent = Intent(this, BreachSiteDetailsActivity::class.java)
        intent.putExtra("breach", item)
        startActivity(intent)
    }


    override fun onClick(v: View) {
        if (v.tag != null){
            println("")
        }
    }

}