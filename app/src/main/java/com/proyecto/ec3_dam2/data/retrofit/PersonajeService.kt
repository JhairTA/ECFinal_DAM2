package com.proyecto.ec3_dam2.data.retrofit

import com.proyecto.ec3_dam2.data.response.ListPersonajeResponse
import retrofit2.http.GET

interface PersonajeService {
    @GET("compendium/all")
    suspend fun getAllPersonajes(): ListPersonajeResponse
}