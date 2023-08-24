package com.proyecto.ec3_dam2.model

import android.os.Parcelable
import android.widget.ImageView
import android.widget.TextView
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "personaje")
@Parcelize
data class Personaje(
   @PrimaryKey
   val id: Int,
   val image: String,
   val name: String,
   val category: String,
   var isFavorite:Boolean = false
): Parcelable

fun getData() : List<Personaje> {
   return listOf(
      Personaje(1,"https://raw.githubusercontent.com/fedeperin/harry-potter-api/main/imagenes/harry_potter.png", "Harry James Potter", "Gryffindor")
   )
}
