package com.proyecto.ec3_dam2.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.os.HandlerCompat
import androidx.core.util.PatternsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.proyecto.ec3_dam2.R
import com.proyecto.ec3_dam2.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var googleLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtén una instancia de SharedPreferences
        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        // Recupera el valor asociado a la clave "email" con valor predeterminado ""
        val email = sharedPreferences.getString("email", "")

        // Recupera el valor asociado a la clave "isGoogleSignIn" con valor predeterminado false
        val isGoogleSignIn = sharedPreferences.getBoolean("isGoogleSignIn", false)

        // Verifica si el usuario ya está autenticado con Firebase o ha iniciado sesión con Google
        if (FirebaseAuth.getInstance().currentUser != null || (isGoogleSignIn && email != "")) {
            // Si el usuario ya está autenticado, redirige a MenuActivity
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish() // Cierra LoginActivity para que no vuelva atrás
            return // Sale del método onCreate para evitar inflar el layout nuevamente
        }

        // Infla el layout después de realizar la verificación de autenticación
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializa firebaseAuth después de inflar el layout
        firebaseAuth = Firebase.auth
        googleLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result ->
            if (result.resultCode == RESULT_OK){
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    val account = task.getResult(ApiException::class.java)
                    authenticateWithFirebase(account.idToken!!)
                }catch (e: Exception){

                }
            }
        }

        binding.btnIngresar.setOnClickListener {
            validate()
        }

        binding.btnGoogle.setOnClickListener {
            loginWithGoogle()
        }

        binding.btnSignUp.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveLoginPreferences(email: String, isGoogleSignIn: Boolean) {
        val sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.putBoolean("isGoogleSignIn", isGoogleSignIn)
        editor.apply()
    }

    private fun authenticateWithFirebase(idToken: String?) {
        val authCredentials = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(authCredentials)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = firebaseAuth.currentUser
                    val account = GoogleSignIn.getLastSignedInAccount(this)
                    if (account != null) {
                        // Guardar en las preferencias compartidas
                        saveLoginPreferences(account.email ?: "", true)
                    }

                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Manejar el error de autenticación
                    Toast.makeText(this, "Error de autenticación con Google", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun loginWithGoogle() {
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_cliente_id))
            .requestEmail()
            .build()

        val googleCliente = GoogleSignIn.getClient(this, googleSignInOptions)
        val intent = googleCliente.signInIntent
        googleLauncher.launch(intent)

    }

    private fun validate(){
        val result = arrayOf(validateEmail(), validatePassword())
        if (false in result){
            return
        }
        val  email = binding.txtEmail.editText?.text.toString()
        val password = binding.txtContrasena.editText?.text.toString()
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if (it.isSuccessful){
                saveLoginPreferences(email, false)
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, "El correo o la contraseña son incorrectos", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun validateEmail(): Boolean{
        val email = binding.txtEmail.editText?.text.toString()
        return  if(email.isEmpty()){
            binding.txtEmail.error = "Este campo no puede estar vacio"
            false
        }else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.txtEmail.error = "Ingrese un correo valido"
            false
        }else{
            binding.txtEmail.error = null
            true
        }
    }


    private fun validatePassword() : Boolean{
        val password = binding.txtContrasena.editText?.text.toString()
        val passwordRegex = Pattern.compile(
            "^" +
                    "(?=\\S+$)" + // No permite espacios
                    ".{8,}" +     // Minimo 8 caracteres
                    "$"
        )
        return if (password.isEmpty()){
            binding.txtContrasena.error = "Este campo no puede estar vacio"
            false
        }else if(!passwordRegex.matcher(password).matches()) {
            //binding.txtContrasena.error = "La contraseña debe tener min 8 caracteres"
            Toast.makeText(this, "El correo o la contraseña son incorrectos", Toast.LENGTH_SHORT).show()
            false
        }else{
            binding.txtContrasena.error = null
            true
        }
    }

}