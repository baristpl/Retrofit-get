package com.example.retrofitgetdata.recycler

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.R

class CardHolder(view: View) : RecyclerView.ViewHolder(view) {
    val thumbnail: ImageView
    val title: TextView
    val subTitle: TextView

    init {
        thumbnail = view.findViewById(R.id.thumbnail)
        title = view.findViewById(R.id.title)
        subTitle = view.findViewById(R.id.sub_title)
    }

}