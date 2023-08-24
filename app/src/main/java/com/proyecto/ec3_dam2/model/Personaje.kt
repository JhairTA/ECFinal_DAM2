package com.proyecto.ec3_dam2.model

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import kotlinx.parcelize.Parcelize

@Parcelize
data class Personaje(
   val image: String,
   val name: String,
   val category: String,
): Parcelable

fun getData() : List<Personaje> {
   return listOf(
      Personaje("https://raw.githubusercontent.com/fedeperin/harry-potter-api/main/imagenes/harry_potter.png", "Harry James Potter", "Gryffindor")
   )
}
