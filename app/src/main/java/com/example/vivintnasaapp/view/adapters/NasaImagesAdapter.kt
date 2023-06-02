package com.example.vivintnasaapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vivintnasaapp.R
import com.example.vivintnasaapp.model.data.nasaimages.Item

class NasaImagesAdapter(val imagesList : List<Item>) : RecyclerView.Adapter<NasaImagesAdapter.MyCustomViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NasaImagesAdapter.MyCustomViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.single_item_row, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: NasaImagesAdapter.MyCustomViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }

    inner class MyCustomViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    }
}