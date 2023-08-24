package com.proyecto.ec3_dam2.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.proyecto.ec3_dam2.model.Personaje

@Dao
interface PersonajeDao {

    @Insert
    suspend fun addPersonajeToFavorite(personaje: Personaje)

    @Query("Select * from personaje")
    suspend fun  getFavorites() : List<Personaje>

    @Delete
    suspend fun eliminarPersonaje(personaje: Personaje)

    @Query("SELECT COUNT(*) FROM personaje WHERE id = :personajeId")
    suspend fun countPersonajeById(personajeId: Int): Int

    //@Query("SELECT * FROM personaje_table WHERE id = :id")
    //suspend fun getPersonajeById(id: Int): Personaje?
}