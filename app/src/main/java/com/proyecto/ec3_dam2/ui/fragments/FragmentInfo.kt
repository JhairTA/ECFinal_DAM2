package com.proyecto.ec3_dam2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proyecto.ec3_dam2.R
import com.proyecto.ec3_dam2.databinding.FragmentInfoBinding

class FragmentInfo : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInfoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }
}