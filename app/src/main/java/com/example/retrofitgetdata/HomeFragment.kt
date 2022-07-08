package com.example.retrofitgetdata

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitgetdata.databinding.FragmentHomeBinding
import com.example.retrofitgetdata.recycler.CardAdapter
import com.example.retrofitgetdata.recycler.GridAdapter

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val universityRecycler = binding.universityRecycler
        val countryRecycler = binding.countryRecycler
        val blogRecycler = binding.blogRecycler
        val departmentRecycler = binding.departmentRecycler


        viewModel.universityContent.observe(viewLifecycleOwner){
            universityRecycler.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    RecyclerView.VERTICAL,
                    false
                )
                adapter = CardAdapter(it, requireContext())
            }
        }

        viewModel.countryContent.observe(viewLifecycleOwner){
            countryRecycler.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    RecyclerView.VERTICAL,
                    false
                )
                adapter = CardAdapter(it, requireContext())
            }
        }

        viewModel.blogContent.observe(viewLifecycleOwner){
            blogRecycler.apply {
                layoutManager = LinearLayoutManager(
                    requireContext(),
                    RecyclerView.VERTICAL,
                    false
                )
                adapter = CardAdapter(it, requireContext())
            }
        }

        viewModel.departmentContent.observe(viewLifecycleOwner) {
            departmentRecycler.apply {
                layoutManager =
                    GridLayoutManager(requireContext(), 2, RecyclerView.VERTICAL, false)
                adapter = GridAdapter(it)
            }
        }
    }
}