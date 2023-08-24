package com.proyecto.ec3_dam2.ui.fragments

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.ec3_dam2.data.db.PersonajeDatabase
import com.proyecto.ec3_dam2.data.repository.PersonajeRepository
import com.proyecto.ec3_dam2.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PersonajeDetailViewModel(application: Application) : AndroidViewModel(application){
    private val repository : PersonajeRepository
    init {
        val db = PersonajeDatabase.getDatabase(application)
        repository = PersonajeRepository(db)
    }

    suspend fun addFavorites(personaje: Personaje): Boolean {
        return withContext(Dispatchers.IO) {
            if (!repository.personajeExists(personaje.id)) {
                repository.addPersonajeFavorites(personaje)
                true
            } else {
                false
            }
        }
    }
    fun eliminarPersonaje(personaje: Personaje) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.eliminarPersonaje(personaje)
        }
    }

    //fun isIdAlreadyExists(id: Int): Boolean {
    //    val existingPersonaje = repository.getPersonajeById(id)
    //    return existingPersonaje != null
    //}

}