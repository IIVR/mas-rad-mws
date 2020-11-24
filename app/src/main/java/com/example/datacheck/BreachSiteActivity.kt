package com.example.datacheck

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_email.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BreachSiteActivity : AppCompatActivity() {

    lateinit var breachList: ArrayList<DataBreach>
    lateinit var adapter: BreachListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breach_site)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://haveibeenpwned.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: HIBPService = retrofit.create(HIBPService::class.java)
        val call: Call<List<DataBreach>> = service.getAllBreaches()

        call.enqueue(object: Callback<List<DataBreach>>, View.OnClickListener {
            override fun onResponse(call: Call<List<DataBreach>>, response: Response<List<DataBreach>>) {

                breachList = response?.body() as ArrayList<DataBreach>
                adapter = BreachListAdapter(breachList, this)

                val recyclerView = findViewById<RecyclerView>(R.id.breach_recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(this@BreachSiteActivity)
                recyclerView.adapter = adapter

            }
            override fun onFailure(call: Call<List<DataBreach>>, t: Throwable) {
                println("Fail " + t)
            }

            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }
        })

    }

}
