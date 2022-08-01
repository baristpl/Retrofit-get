package com.example.retrofitgetdata.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.databinding.CardListItemBinding
import com.example.retrofitgetdata.models.CardModel

class CardAdapter() :
   ListAdapter<CardModel, CardHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = CardListItemBinding.inflate(LayoutInflater.from(parent.context))
        return CardHolder(view)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<CardModel>() {
        override fun areItemsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem.thumbnail == newItem.thumbnail
        }

        override fun areContentsTheSame(oldItem: CardModel, newItem: CardModel): Boolean {
            return oldItem.thumbnail == newItem.thumbnail
                    && oldItem.subTitle == newItem.subTitle
                    && oldItem.title == newItem.title
        }
    }
}

class CardHolder(private val binding: CardListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(cardModel: CardModel){
        binding.cardModel = cardModel
    }
}