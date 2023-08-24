package com.proyecto.ec3_dam2.data.repository

import com.proyecto.ec3_dam2.data.db.PersonajeDao
import com.proyecto.ec3_dam2.data.db.PersonajeDatabase
import com.proyecto.ec3_dam2.data.retrofit.RetrofitHelper
import com.proyecto.ec3_dam2.model.Personaje

class PersonajeRepository(val db: PersonajeDatabase? = null) {
    private val dao: PersonajeDao? = db?.personajeDao()
    suspend fun getPersonajes() : List<Personaje>{
        val response = RetrofitHelper.personajeService.getAllPersonajes()
        return response.data
    }

    suspend fun getFavotires(): List<Personaje>{
        dao?.let {
            return dao.getFavorites()
        } ?: kotlin.run {
            return listOf()
        }
    }

    suspend fun  addPersonajeFavorites(personaje: Personaje){
        dao?.let {
            dao.addPersonajeToFavorite(personaje)
        }
    }

    suspend fun personajeExists(personajeId: Int): Boolean {
        dao?.let {
            val count = dao.countPersonajeById(personajeId)
            return count > 0
        } ?: kotlin.run {
            return false
        }
    }

    suspend fun eliminarPersonaje(personaje: Personaje) {
        dao?.eliminarPersonaje(personaje)
    }

    //suspend fun getPersonajeById(id: Int): Personaje? {
    //    return dao?.getPersonajeById(id)
    //}
}