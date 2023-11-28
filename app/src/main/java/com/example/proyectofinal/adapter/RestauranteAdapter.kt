package com.example.proyectofinal.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinal.R
import com.example.proyectofinal.model.RestaurantModel
import com.squareup.picasso.Picasso

class RestauranteAdapter (private var restauranteList: List<RestaurantModel>)
    : RecyclerView.Adapter<RestauranteAdapter.ViewHolder>()
{
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val imgLogoR: ImageView = itemView.findViewById(R.id.imgLogoR)
        val imgLogoCom: ImageView = itemView.findViewById(R.id.imgLogoCom)
        val tvTiempo: TextView = itemView.findViewById(R.id.tvTiempo)
        val tvNomrest: TextView = itemView.findViewById(R.id.tvNomrest)
        val rbarRest:  RatingBar = itemView.findViewById(R.id.rbarRest)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
            return ViewHolder(layoutInflater.inflate(R.layout.item_restaurante, parent, false))

    }

    override fun getItemCount(): Int {
        return restauranteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemRestaurante = restauranteList[position]
        holder.tvNomrest.text = itemRestaurante.nombreRest
        holder.tvTiempo.text = itemRestaurante.tiempo
        Picasso.get().load(itemRestaurante.logoUrl).into(holder.imgLogoR)
        Picasso.get().load(itemRestaurante.comidaUrl).into(holder.imgLogoCom)
        holder.rbarRest.rating = itemRestaurante.rating ?: 0.0f
    }

}