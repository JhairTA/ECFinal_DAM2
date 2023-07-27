package com.proyecto.ec3_dam2.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyecto.ec3_dam2.data.repository.PersonajeRepository
import com.proyecto.ec3_dam2.model.Personaje
import com.proyecto.ec3_dam2.model.getData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {
    val personajes: MutableLiveData<List<Personaje>> = MutableLiveData<List<Personaje>>()
    val repository = PersonajeRepository()

    fun getAllPersonajes(){
        val personajeList = getData()
        personajes.value = personajeList
    }

    fun getPersonajesFromService(){
        viewModelScope.launch (Dispatchers.IO ){
            val response = repository.getPersonajes()
            personajes.postValue(response)
        }
    }
}