package com.proyecto.ec3_dam2.ui.fragments

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.proyecto.ec3_dam2.data.db.PersonajeDatabase
import com.proyecto.ec3_dam2.data.repository.PersonajeRepository
import com.proyecto.ec3_dam2.model.Personaje
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonajeFavoriteViewModel(application: Application) : AndroidViewModel(application){
    private val repository : PersonajeRepository
    private var _favorites: MutableLiveData<List<Personaje>> = MutableLiveData()
    var favorites: LiveData<List<Personaje>> = _favorites

    init {
        val db = PersonajeDatabase.getDatabase(application)
        repository = PersonajeRepository(db)
    }

    fun getFavorites(){
        viewModelScope.launch(Dispatchers.IO){
            val data = repository.getFavotires()
            _favorites.postValue(data)
        }
    }
}