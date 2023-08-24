package com.proyecto.ec3_dam2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.os.HandlerCompat
import com.proyecto.ec3_dam2.R
import com.proyecto.ec3_dam2.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        HandlerCompat.postDelayed(Handler(), {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, null, 3000)
    }
}