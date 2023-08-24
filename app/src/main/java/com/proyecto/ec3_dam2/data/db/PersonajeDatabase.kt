package com.proyecto.ec3_dam2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proyecto.ec3_dam2.model.Personaje

@Database(entities = [Personaje::class], version = 1)
abstract class PersonajeDatabase: RoomDatabase() {
    abstract fun personajeDao(): PersonajeDao

    companion object{
        @Volatile
        private var instance: PersonajeDatabase? = null
        fun getDatabase(context: Context): PersonajeDatabase{
            val tempIntance = instance
            if (tempIntance != null){
                return tempIntance
            }
            synchronized(this){
                val _instance = Room.databaseBuilder(context.applicationContext, PersonajeDatabase::class.java, "personajedb").build()
                instance = _instance
                return  _instance
            }
        }
    }
}