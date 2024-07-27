package com.joguco.segundocursokotlin.SuperheroApp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joguco.segundocursokotlin.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperheroViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(Superhero: Superhero, onItemSelected:(String) -> Unit){
        binding.tvSuperheroName.text = Superhero.nombre
        Picasso.get().load(Superhero.image.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener{ onItemSelected(Superhero.superheroId) }
    }
}