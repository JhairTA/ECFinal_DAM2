package com.proyecto.ec3_dam2.data.repository

import com.proyecto.ec3_dam2.data.retrofit.RetrofitHelper
import com.proyecto.ec3_dam2.model.Personaje

class PersonajeRepository {

    suspend fun getPersonajes() : List<Personaje>{
        val response = RetrofitHelper.personajeService.getAllPersonajes()
        return response.data
    }
}