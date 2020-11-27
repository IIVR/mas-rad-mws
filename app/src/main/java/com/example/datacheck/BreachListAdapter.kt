package com.example.datacheck

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.*

class BreachListAdapter(val breachList: ArrayList<DataBreach>, val clickListener: (DataBreach, Int) -> Unit)
    : RecyclerView.Adapter<BreachListAdapter.ViewHolder>(), Filterable {


    var breachFilterList = ArrayList<DataBreach>()

    init {
        breachFilterList = breachList
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val cardView = itemView.findViewById(R.id.card_view) as CardView
        val titleView = cardView.findViewById(R.id.titleBreach) as TextView
        val imageView = cardView.findViewById(R.id.logoBreach) as ImageView
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreachListAdapter.ViewHolder {
        val viewItem = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_breach, parent, false)

        return ViewHolder(viewItem)
    }

    override fun onBindViewHolder(holder: BreachListAdapter.ViewHolder, position: Int) {
        var item : DataBreach = breachFilterList[position]

        holder.cardView.setOnClickListener { clickListener(item, position) }

        holder.cardView.tag = position
        holder.titleView.text = item.title
        var logoUrl: String = item.logoPath
        Picasso.get().load(logoUrl).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return breachFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    breachFilterList = breachList
                } else {
                    val resultList = ArrayList<DataBreach>()
                    for (row in breachList) {
                        if (row.title.toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    breachFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = breachFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                breachFilterList = results?.values as ArrayList<DataBreach>
                notifyDataSetChanged()
            }

        }
    }



}