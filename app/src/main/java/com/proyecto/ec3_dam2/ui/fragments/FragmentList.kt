package com.proyecto.ec3_dam2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyecto.ec3_dam2.R
import com.proyecto.ec3_dam2.databinding.FragmentListBinding
import com.proyecto.ec3_dam2.model.getData
import com.proyecto.ec3_dam2.ui.viewmodels.ListViewModel


class FragmentList : Fragment() {
    private lateinit var binding: FragmentListBinding
    private lateinit var viewModel: ListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RVPersListAdapter(listOf()){personaje ->
            val destination = FragmentListDirections.actionFragmentListToFragmentDetail(personaje)
            findNavController().navigate(destination)
        }
        binding.rvPersList.adapter = adapter
        viewModel.personajes.observe(requireActivity()){
            adapter.pers = it
            adapter.notifyDataSetChanged()
        }
        viewModel.getPersonajesFromService()
    }

}