package com.example.retrofitgetdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.retrofitgetdata.databinding.FragmentHomeBinding
import com.example.retrofitgetdata.recycler.CardAdapter
import com.example.retrofitgetdata.recycler.GridAdapter

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.universityRecycler.adapter = CardAdapter(requireContext())
        binding.countryRecycler.adapter = CardAdapter(requireContext())
        binding.blogRecycler.adapter = CardAdapter(requireContext())
        binding.departmentRecycler.adapter = GridAdapter()

        return binding.root
    }

}