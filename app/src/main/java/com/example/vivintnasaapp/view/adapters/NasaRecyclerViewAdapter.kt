package com.example.vivintnasaapp.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.vivintnasaapp.R
import com.example.vivintnasaapp.model.data.nasaimages.Item

class NasaRecyclerViewAdapter(private val context : Context, private val imagesList : List<Item>) : RecyclerView.Adapter<NasaRecyclerViewAdapter.MyCustomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NasaRecyclerViewAdapter.MyCustomViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_row, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: NasaRecyclerViewAdapter.MyCustomViewHolder, position: Int) {
        val nasaImageObject = imagesList[position]
        holder.titleTV.text = nasaImageObject.data.first().title
        holder.descTV.text = nasaImageObject.data.first().description
        Glide.with(context).load(nasaImageObject.links.first().href).into(holder.nasaIV);
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    inner class MyCustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val titleTV = itemView.findViewById<TextView>(R.id.titleTV)
        val descTV = itemView.findViewById<TextView>(R.id.descTV)
        val nasaIV = itemView.findViewById<ImageView>(R.id.nasaIV)
    }
}