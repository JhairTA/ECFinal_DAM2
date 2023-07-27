package com.proyecto.ec3_dam2.model

import android.widget.ImageView
import android.widget.TextView

data class Personaje(
   val image: String,
   val name: String,
   val category: String,
)

fun getData() : List<Personaje> {
   return listOf(
      Personaje("https://raw.githubusercontent.com/fedeperin/harry-potter-api/main/imagenes/harry_potter.png", "Harry James Potter", "Gryffindor")
   )
}
