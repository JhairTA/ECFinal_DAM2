package com.proyecto.ec3_dam2.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.proyecto.ec3_dam2.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnIngresar.setOnClickListener {
            validate()
        }
    }

    private fun validate(){
        val correo = binding.txtEmail.editText?.text.toString()
        val contra = binding.txtContrasena.editText?.text.toString()

        if (correo.isNotEmpty() && contra.isNotEmpty()){
            if (Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                if (correo.equals("ejemplo@idat.edu.pe") && contra.equals("123456")){
                    Toast.makeText(this, "Bienvenido al app", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }else{
                    Toast.makeText(this, "Correo electrónico o contraseña incorrectos ", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Debes ingresar un correo valido ", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Debebe conpletar todos los campos ", Toast.LENGTH_SHORT).show()
        }
    }
}