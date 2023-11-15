package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.proyectofinal.model.UserModel
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNombreR: EditText = findViewById(R.id.etNombreR)
        val etApellidoR: EditText = findViewById(R.id.etApellidoR)
        val etAnionacimientoR: EditText = findViewById(R.id.etAnionacimientoR)
        val etCorreoR: EditText = findViewById(R.id.etCorreoR)
        val etContraseniaR: EditText = findViewById(R.id.etContraseniaR)
        val btnSaveRegister: Button = findViewById(R.id.btnRegistrar)

        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("usuarios")

        btnSaveRegister.setOnClickListener {
            val nombres = etNombreR.text.toString()
            val apellidos = etApellidoR.text.toString()
            val anionacimiento = etAnionacimientoR.text.toString()
            val correo = etCorreoR.text.toString()
            val contrasenia = etContraseniaR.text.toString()

            auth.createUserWithEmailAndPassword(correo, contrasenia)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        //Se registró en Firebase Authentication y
                        // ahora registramos en Firebase Firestore
                        var user: FirebaseUser? = auth.currentUser
                        var uid = user?.uid
                        var userModel =
                            UserModel(
                                correo,
                                contrasenia,
                                apellidos,
                                nombres,
                                anionacimiento,
                                uid.toString()
                            )
                        collectionRef.add(userModel)
                            .addOnCompleteListener {
                                Snackbar
                                    .make(
                                        findViewById(android.R.id.content),
                                        "Registro exitoso",
                                        Snackbar.ANIMATION_MODE_FADE
                                    ).show()

                                etApellidoR.setText("")
                                etNombreR.setText("")
                                etAnionacimientoR.setText("")
                                etCorreoR.setText("")
                                etContraseniaR.setText("")

                                // Redirección a ConfirmacionActivity
                                val intent = Intent(this, ConfirmacionActivity::class.java)
                                startActivity(intent)



                            }.addOnFailureListener {
                                Snackbar
                                    .make(
                                        findViewById(android.R.id.content),
                                        "No se pudo completar la transacción",
                                        Snackbar.LENGTH_LONG
                                    ).show()
                            }
                    } else {
                        Snackbar
                            .make(
                                findViewById(android.R.id.content),
                                "Ocurrió un error al registrarse: ${task.exception?.message}",
                                Snackbar.LENGTH_LONG
                            ).show()
                    }
                }


        }
        //linkIngresa
        val etIngresa: TextView = findViewById(R.id.etIngresa)

        etIngresa.setOnClickListener {
            // Intent para dirigirte a LoginActivity
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)


        }
    }
}