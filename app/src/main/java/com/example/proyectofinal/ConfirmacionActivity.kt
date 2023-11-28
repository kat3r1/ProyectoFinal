package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.proyectofinal.fragments.PrincipalFragment

class ConfirmacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmacion)

        val btnOrdena: Button = findViewById(R.id.btnOrdena)

        btnOrdena.setOnClickListener {
            // Intent para dirigirte a PrincipalActivity
            val intent = Intent(this, PrincipalFragment::class.java)
            startActivity(intent)
        }

    }
}