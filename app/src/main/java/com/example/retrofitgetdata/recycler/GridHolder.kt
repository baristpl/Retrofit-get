package com.example.retrofitgetdata.recycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.R

class GridHolder(view: View) : RecyclerView.ViewHolder(view) {
    val title: TextView

    init {
        title = view.findViewById(R.id.title)
    }
}