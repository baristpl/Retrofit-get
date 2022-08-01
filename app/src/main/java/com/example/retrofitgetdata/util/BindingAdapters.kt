package com.example.retrofitgetdata

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.models.CardModel
import com.example.retrofitgetdata.ui.recycler.CardAdapter
import com.example.retrofitgetdata.ui.recycler.GridAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("linearListData")
fun bindLinearRecyclerView(
    recyclerView: RecyclerView,
    data: List<CardModel>?
) {
    val adapter = recyclerView.adapter as CardAdapter
    if (data != null) {
        adapter.submitList(data)
    }
}

@BindingAdapter("gridListData")
fun bindGridRecyclerView(
    recyclerView: RecyclerView,
    data: List<String>?
) {
    val adapter = recyclerView.adapter as GridAdapter
    if (data != null) {
        adapter.submitList(data)
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String) {
    val context = imageView.context
    Picasso.with(context).load(imageUrl).into(imageView)
}