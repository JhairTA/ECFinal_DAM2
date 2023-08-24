package com.proyecto.ec3_dam2.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.proyecto.ec3_dam2.databinding.FragmentDetailBinding
import com.proyecto.ec3_dam2.model.Personaje
import kotlinx.coroutines.launch


class FragmentDetail : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val args: FragmentDetailArgs by navArgs()
    private lateinit var personaje: Personaje
    private lateinit var viewModel: PersonajeDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        personaje = args.personaje
        viewModel = ViewModelProvider(requireActivity())[PersonajeDetailViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(this).load(personaje.image).into(binding.detailImage)
        binding.detailNombre.text = personaje.name
        binding.detailCategoria.text = personaje.category
        if(personaje.isFavorite){
            binding.btnAddFavorito.visibility = View.GONE
            binding.btnAgregar.visibility= View.GONE
            binding.btnEliminar.visibility =View.VISIBLE
        }
        binding.btnAgregar.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val added = viewModel.addFavorites(personaje)
                if (added) {
                    Snackbar.make(binding.root, "Agregado a favoritos", Snackbar.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "Item ya se encuentra en la tabla", Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnEliminar.setOnClickListener {
            viewModel.eliminarPersonaje(personaje)
            Snackbar.make(binding.root, "Eliminado de favoritos", Snackbar.LENGTH_SHORT).show()
            // Opcional: Navegar de regreso a la pantalla anterior
            findNavController().navigateUp()
        }
    }

}