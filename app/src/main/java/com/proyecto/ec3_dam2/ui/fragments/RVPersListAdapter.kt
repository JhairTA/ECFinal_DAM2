package com.proyecto.ec3_dam2.ui.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.proyecto.ec3_dam2.databinding.ItemPersonajesBinding
import com.proyecto.ec3_dam2.model.Personaje


class RVPersListAdapter(var pers: List<Personaje>): RecyclerView.Adapter<PersVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersVH {
        val binding = ItemPersonajesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersVH(binding)
    }

    override fun getItemCount(): Int = pers.size

    override fun onBindViewHolder(holder: PersVH, position: Int) {
        holder.bind(pers[position])
    }
}

class PersVH(private val binding: ItemPersonajesBinding): ViewHolder(binding.root){
    fun bind(personaje: Personaje) {
        Glide.with(binding.root.context).load(personaje.image).into(binding.imgPersonaje)
        binding.txtNombre.text = personaje.name
        binding.txtCategoria.text = personaje.category
    }

}