package com.example.datacheck

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_breached_account.*
import java.util.*


class BreachedAccountActivity : AppCompatActivity() {

    private var breachList = ArrayList<DataBreach>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_breached_account)

        breachList = intent.getSerializableExtra("breachList") as ArrayList<DataBreach>

        var breachAdapter = BreachAdapter(this, breachList)

        lvBreach.adapter = breachAdapter

        lvBreach.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(this, "Click on " + breachList[position].title, Toast.LENGTH_SHORT).show()

        }

    }

    inner class BreachAdapter : BaseAdapter {

        private var breachList = ArrayList<DataBreach>()
        private var context: Context? = null

        constructor(context: Context, breachList: ArrayList<DataBreach>) : super() {
            this.breachList = breachList
            this.context = context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

            val view: View?
            val vh: ViewHolder

            if (convertView == null) {
                view = layoutInflater.inflate(R.layout.breach, parent, false)
                vh = ViewHolder(view)
                view.tag = vh
            } else {
                view = convertView
                vh = view.tag as ViewHolder

            }

            vh.Title.text = breachList[position].title

            var logoUrl: String = breachList[position].logoPath
            Picasso.get().load(logoUrl).into(vh.Icon);

            return view
        }

        override fun getItem(position: Int): Any {
            return breachList[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return breachList.size
        }
    }

    private class ViewHolder(view: View?) {
        val Title: TextView = view?.findViewById(R.id.Title) as TextView
        val Icon: ImageView = view?.findViewById(R.id.breachIcon) as ImageView

    }


}