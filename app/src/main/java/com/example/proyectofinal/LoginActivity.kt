package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var auth = FirebaseAuth.getInstance()

        val txtCorreoLogin: EditText = findViewById(R.id.txtCorreoLogin)
        val txtContraseniaLogin: EditText = findViewById(R.id.txtContraseniaLogin)
        val btnIngresar: Button = findViewById(R.id.btnIngresar)
        val btnRegistrarse: Button = findViewById(R.id.btnRegistrarse)

        btnIngresar.setOnClickListener {
            val correo = txtCorreoLogin.text.toString()
            val clave = txtContraseniaLogin.text.toString()

            auth.signInWithEmailAndPassword(correo,clave)
                .addOnCompleteListener(this) {task ->
                    if(task.isSuccessful){
                        Snackbar
                            .make(findViewById(android.R.id.content),
                                "Ingreso exitoso",
                                Snackbar.LENGTH_LONG).show()
                        startActivity(Intent(this, NavigationActivity::class.java))
                    }else{
                        Snackbar
                            .make(findViewById(android.R.id.content),
                                "Credenciales inválidas",
                                Snackbar.LENGTH_LONG).show()
                    }
                }
        }

        btnRegistrarse.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}