package com.example.retrofitgetdata.recycler


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.R
import com.example.retrofitgetdata.models.CardModel
import com.squareup.picasso.Picasso


class CardAdapter(private val data: ArrayList<CardModel>, private val context: Context) :
    RecyclerView.Adapter<CardHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {


        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_list_item, parent, false)
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.title.text = data[position].title

        if (data[position].subTitle != "")
            holder.subTitle.text = data[position].subTitle

        Picasso.with(context).load(data[position].thumbnail).into(holder.thumbnail)

    }

    override fun getItemCount(): Int {
        return data.size
    }
}