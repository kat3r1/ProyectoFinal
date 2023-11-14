package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.firestore
import android.os.Handler
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Agrega un retraso de 2000 milisegundos (2 segundos)
        Handler().postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()  // Cierra la actividad actual para que no puedas volver atrás
        }, 2000)

        val db = FirebaseFirestore.getInstance()


        db.collection("prueba")
            .addSnapshotListener{snapshots, e ->
                if(e!=null){
                    Snackbar
                        .make(findViewById(android.R.id.content),
                            "Error al conectarse a firestore",
                            Snackbar.LENGTH_LONG).show()
                    return@addSnapshotListener
                }

                for(dc in snapshots!!.documentChanges){
                    when(dc.type){
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED -> {
                            Snackbar
                                .make(findViewById(android.R.id.content),
                                    "Consultando...",
                                    Snackbar.LENGTH_LONG).show()
                        }

                        DocumentChange.Type.REMOVED -> {
                            Snackbar
                                .make(findViewById(android.R.id.content),
                                    "El documento fue eliminado",
                                    Snackbar.LENGTH_LONG).show()
                        }
                        else -> {
                            Snackbar
                                .make(findViewById(android.R.id.content),
                                    "Error al consultar la colección",
                                    Snackbar.LENGTH_LONG).show()
                        }
                    }

                }

            }
    }
}