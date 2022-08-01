package com.example.retrofitgetdata.ui.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.databinding.GridItemBinding

class GridAdapter :
    ListAdapter<String, GridHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridHolder {
        val view = GridItemBinding.inflate(LayoutInflater.from(parent.context))
        return GridHolder(view)
    }

    override fun onBindViewHolder(holder: GridHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object DiffCallback : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }
}

class GridHolder(val binding: GridItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(departmentName: String) {
        binding.text = departmentName
    }
}