package com.example.retrofitgetdata.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.R

class GridAdapter() :
    RecyclerView.Adapter<GridHolder>() {
    private var data = arrayListOf<String>()

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

    fun setData(newData: ArrayList<String>){
        this.data.clear()
        this.data.addAll(newData)
    }
}

class GridHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView

    init {
        title = view.findViewById(R.id.title)
    }
}