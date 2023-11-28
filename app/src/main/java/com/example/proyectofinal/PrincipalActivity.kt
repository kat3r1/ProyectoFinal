
//ACTIVIDAD NO SIRVE DEBE SER ELIMINADA
package com.example.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectofinal.databinding.ActivityPrincipalBinding

class PrincipalActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPrincipalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

      //  binding.imgbtnFragment.setOnClickListener {
            // Intent para dirigirte a ActivityNavigation en NavigationActivity
        //    val intent = Intent(this, NavigationActivity::class.java)
          //  intent.putExtra("fragmentName", "activity_navigation") // Puedes pasar información adicional si es necesario
           // startActivity(intent)
   //     }
    }
}

