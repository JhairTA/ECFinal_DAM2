package com.proyecto.ec3_dam2.ui.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.proyecto.ec3_dam2.R
import com.proyecto.ec3_dam2.databinding.FragmentInfoBinding
import com.proyecto.ec3_dam2.ui.LoginActivity

class FragmentInfo : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentInfoBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment

        binding.btnCerraSesion.setOnClickListener {
            signOut()
        }

        return binding.root
    }

    private fun signOut() {
        // Cierra la sesión actual y redirige a LoginActivity
        FirebaseAuth.getInstance().signOut()

        // Limpia los datos de inicio de sesión guardados en SharedPreferences
        val sharedPreferences = requireContext().getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit()
            .remove("email")
            .remove("isGoogleSignIn")
            .apply()

        // Redirige a LoginActivity
        val intent = Intent(requireContext(), LoginActivity::class.java)
        startActivity(intent)
        requireActivity().finish() // Cierra la actividad actual
    }
}