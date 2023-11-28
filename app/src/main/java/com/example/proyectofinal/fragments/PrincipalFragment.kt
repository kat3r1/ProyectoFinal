package com.example.proyectofinal.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.adapter.RestauranteAdapter
import com.example.proyectofinal.model.RestaurantModel
import com.google.firebase.firestore.FirebaseFirestore


class PrincipalFragment : Fragment() {
     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view: View = inflater.inflate(R.layout.fragment_principal, container, false)

         val db = FirebaseFirestore.getInstance()
         var lstRestaurante: List<RestaurantModel>
         val rvRestaurant: RecyclerView = view.findViewById(R.id.rvRestaurant)

         db.collection("restaurantes")
             .addSnapshotListener{ snap, error ->
                 if(error!=null){

                     return@addSnapshotListener
                 }
                 lstRestaurante = snap!!.documents.map {document ->
                     RestaurantModel(
                         document["comidaUrl"].toString(),
                         document["logoUrl"].toString(),
                         document["nombreRest"].toString(),
                         document["tiempo"].toString(),
                         document["rating"]?.toString()?.toFloatOrNull()
                     )
                 }
                 rvRestaurant.adapter = RestauranteAdapter(lstRestaurante)
                 rvRestaurant.layoutManager = LinearLayoutManager(requireContext())
             }
         return view


    }


}