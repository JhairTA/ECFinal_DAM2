package com.proyecto.ec3_dam2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.proyecto.ec3_dam2.R
import com.proyecto.ec3_dam2.databinding.ActivityMenuBinding

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val  navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_ev2) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bnvMenu.setupWithNavController(navController)

        /**binding.bnvMenu.setOnItemSelectedListener { item ->
            when (item.itemId){
                R.id.lista ->{
                    binding.textHello.text = "Lista"
                    true
                }
                R.id.favorito ->{
                    binding.textHello.text = "favorito"
                    true
                }
                R.id.info ->{
                    binding.textHello.text = "info"
                    true
                }else -> {
                    false
                }
            }

        }**/
    }
}