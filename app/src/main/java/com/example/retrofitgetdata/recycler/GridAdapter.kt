package com.example.retrofitgetdata.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.R

class GridAdapter(private val data: ArrayList<String>) :
    RecyclerView.Adapter<GridHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.grid_item, parent, false)
        return GridHolder(view)
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        holder.title.text = data[position]
    }

    override fun getItemCount(): Int {
        return data.size
    }
}