package com.itesm.tarea3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

open class AdapterFood (var list: ArrayList<Food>) : RecyclerView.Adapter<AdapterFood.ViewHolder>(){

    class ViewHolder(view:View): RecyclerView.ViewHolder(view){
        var name: TextView = itemView.findViewById(R.id.txtName)
        var price: TextView = itemView.findViewById(R.id.txtPrice)
        var cardView: CardView = itemView.findViewById(R.id.cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)

        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var name = list[position].name
        var price = list[position].price
        var description = list[position].description

        holder.name.text = name
        holder.price.text = ("$$price")

        holder.cardView.setOnClickListener{
            Toast.makeText(holder.itemView.context,
                "Description: $description",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}