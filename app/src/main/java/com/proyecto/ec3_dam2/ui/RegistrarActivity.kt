package com.proyecto.ec3_dam2.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.util.PatternsCompat
import com.google.firebase.auth.FirebaseAuth
import com.proyecto.ec3_dam2.databinding.ActivityRegistrarBinding
import java.util.regex.Pattern
import com.proyecto.ec3_dam2.R

class RegistrarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrarBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnRegistrar.setOnClickListener {
            validate()
        }

        binding.txtInicio.setOnClickListener{
            val loginIntent= Intent(this,LoginActivity::class.java)
            startActivity(loginIntent)
        }

    }

    private fun validate(){
        val correo=binding.edCorreo.editText?.text.toString()
        val contraseña=binding.edContra.editText?.text.toString()
        val result = arrayOf(validateEmail(), validatePassword(), validateConfirPassword())
        if (false in result){
            return
        }
        firebaseAuth.createUserWithEmailAndPassword(correo,contraseña).addOnCompleteListener{
            if(it.isSuccessful){
                val builder = AlertDialog.Builder(this)
                val view = layoutInflater.inflate(R.layout.usuario_registrado,null)

                builder.setView(view)
                val dialog = builder.create()

                view.findViewById<Button>(R.id.btnOk).setOnClickListener {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    dialog.dismiss()
                }

                if (dialog.window != null){
                    dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
                }
                dialog.show()
            } else{
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateEmail(): Boolean{
        val correo=binding.edCorreo.editText?.text.toString()
        return  if(correo.isEmpty()){
            binding.edCorreo.error = "Este campo no puede estar vacio"
            false
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(correo).matches()) {
            binding.edCorreo.error = "Ingrese un correo valido"
            false
        }else{
            binding.edCorreo.error = null
            true
        }
    }

    private fun validatePassword() : Boolean{
        val contraseña=binding.edContra.editText?.text.toString()
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=\\S+$)" + // No permite espacios
                    ".{8,}" +     // Minimo 8 caracteres
                    "$"
        )
        return if (contraseña.isEmpty()){
            binding.edContra.error = "Este campo no puede estar vacio"
            false
        }else if(!passwordRegex.matcher(contraseña).matches()) {
            binding.edContra.error = "La contraseña debe tener min 8 caracteres"
            false
        }else if(contraseña.length>16){
            binding.edContra.error = "La contraseña debe tener max 16 caracteres"
            false
        }else{
            binding.edContra.error = null
            true
        }
    }

    private fun validateConfirPassword() : Boolean{
        val confcontra=binding.edCofcontra.editText?.text.toString()
        val contraseña=binding.edContra.editText?.text.toString()
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=\\S+$)" + // No permite espacios
                    ".{8,}" +     // Minimo 8 caracteres
                    "$"
        )
        return if (confcontra.isEmpty()){
            binding.edCofcontra.error = "Este campo no puede estar vacio"
            false
        }else if(!confcontra.equals(contraseña)) {
            binding.edCofcontra.error = "Las contraseñas no coinciden"
            false
        }else{
            binding.edCofcontra.error = null
            true
        }
    }
}