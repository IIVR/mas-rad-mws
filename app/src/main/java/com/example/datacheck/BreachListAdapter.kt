package com.example.datacheck

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.util.ArrayList

class BreachListAdapter(val breachList: ArrayList<DataBreach>, val itemClickListener: View.OnClickListener)
    : RecyclerView.Adapter<BreachListAdapter.ViewHolder>() {

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
        val breach = breachList[position]
        holder.cardView.setOnClickListener(itemClickListener)
        holder.cardView.tag = position
        holder.titleView.text = breach.title
        var logoUrl: String = breach.logoPath
        Picasso.get().load(logoUrl).into(holder.imageView);

    }

    override fun getItemCount(): Int {
        return breachList.size
    }

}