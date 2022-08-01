package com.example.retrofitgetdata.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.retrofitgetdata.HomeViewModel
import com.example.retrofitgetdata.HomeViewModelFactory
import com.example.retrofitgetdata.database.CardDatabase
import com.example.retrofitgetdata.databinding.FragmentHomeBinding
import com.example.retrofitgetdata.ui.recycler.CardAdapter
import com.example.retrofitgetdata.ui.recycler.GridAdapter

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels{
        HomeViewModelFactory(CardDatabase.getInstance(requireContext()).databaseDao)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.universityRecycler.adapter = CardAdapter()
        binding.countryRecycler.adapter = CardAdapter()
        binding.blogRecycler.adapter = CardAdapter()
        binding.departmentRecycler.adapter = GridAdapter()

        viewModel.blogContent.observe(viewLifecycleOwner){
            Log.d("blogContent", it.toString() )
        }

        return binding.root
    }

}