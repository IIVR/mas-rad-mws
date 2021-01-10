package com.example.datacheck.presentation

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.datacheck.*
import com.example.datacheck.model.DataBreach
import com.example.datacheck.presentation.adapter.BreachListAdapter
import com.example.datacheck.service.HIBPService
import kotlinx.android.synthetic.main.activity_breach_site.*
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

        search_bar.setIconifiedByDefault(false)
        search_bar.requestFocus()

        search_bar.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        val retrofit = Retrofit.Builder()
            .baseUrl("https://haveibeenpwned.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: HIBPService = retrofit.create(HIBPService::class.java)
        val call: Call<List<DataBreach>> = service.getAllBreaches()

        call.enqueue(object : Callback<List<DataBreach>>, View.OnClickListener {
            override fun onResponse(
                    call: Call<List<DataBreach>>,
                    response: Response<List<DataBreach>>
            ) {

                breachList = response?.body() as ArrayList<DataBreach>

                adapter = BreachListAdapter(breachList){ item: DataBreach, position: Int ->
                    println("Clicked on item  ${item.title} at position $position")
                    showDetails(item)
                }
                val recyclerView = findViewById<RecyclerView>(R.id.breach_recycler_view)
                recyclerView.layoutManager = LinearLayoutManager(this@BreachSiteActivity)
                recyclerView.adapter = adapter
            }

            override fun onFailure(call: Call<List<DataBreach>>, t: Throwable) {
                println("Fail " + t)
            }

            override fun onClick(v: View?) {

            }
        })

    }

    fun showDetails(item: DataBreach) {

        val intent = Intent(this, BreachSiteDetailsActivity::class.java)
        intent.putExtra("breach", item)
        startActivity(intent)
    }

}
