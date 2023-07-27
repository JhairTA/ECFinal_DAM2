package com.proyecto.ec3_dam2.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitHelper {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://botw-compendium.herokuapp.com/api/v3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val personajeService: PersonajeService = retrofit.create(PersonajeService::class.java)
}

//https://run.mocky.io/v3/3c8325a3-0942-42dd-a98a-3d4e566c5f7c