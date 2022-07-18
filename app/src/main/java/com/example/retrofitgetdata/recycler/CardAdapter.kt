package com.example.retrofitgetdata.recycler

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.R
import com.example.retrofitgetdata.models.CardModel
import com.squareup.picasso.Picasso

class CardAdapter( private val context: Context) :
    RecyclerView.Adapter<CardHolder>() {
    private var data: ArrayList<CardModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_list_item, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.title.text = data[position].title
        holder.subTitle.text = data[position].subTitle
        Picasso.with(context).load(data[position].thumbnail).into(holder.thumbnail)
    }

    fun setData(newData: ArrayList<CardModel>) {
        this.data.clear()
        this.data.addAll(newData)
    }
    override fun getItemCount(): Int {
        return data.size
    }
}

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