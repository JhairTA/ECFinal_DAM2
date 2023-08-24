package com.proyecto.ec3_dam2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyecto.ec3_dam2.R
import com.proyecto.ec3_dam2.databinding.FragmentFavoriteBinding

class FragmentFavorite : Fragment() {
    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: PersonajeFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[PersonajeFavoriteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVPersListAdapter(listOf()){personaje ->
            val destination = FragmentFavoriteDirections.actionFragmentFavoriteToFragmentDetail(personaje)
            findNavController().navigate(destination)
        }
        binding.rvFavorites.adapter = adapter
        viewModel.favorites.observe(requireActivity()){
            adapter.pers = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getFavorites()
    }
}