package com.example.retrofitgetdata

import android.util.Log
import android.widget.ArrayAdapter
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.models.CardModel
import com.example.retrofitgetdata.recycler.CardAdapter
import com.example.retrofitgetdata.recycler.GridAdapter
import java.lang.Exception

@BindingAdapter("linearListData")
fun bindLinearRecyclerView(recyclerView: RecyclerView,
                     data: LiveData<ArrayList<CardModel>>) {
    val adapter = recyclerView.adapter as CardAdapter
    if (data.value != null) {
        adapter.setData(data.value!!)
        adapter.notifyDataSetChanged()
    }
}

@BindingAdapter("gridListData")
fun bindGridRecyclerView(recyclerView: RecyclerView,
                         data: ArrayList<String>?) {
    val adapter = recyclerView.adapter as GridAdapter
    if (data != null) {
        adapter.setData(data)
        adapter.notifyDataSetChanged()
    }
}