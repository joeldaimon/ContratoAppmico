package com.joguco.segundocursokotlin.SuperheroApp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joguco.segundocursokotlin.R

class SuperheroAdapter(var superheroList: List<Superhero> = emptyList(),
                       private val onItemSelected:(String) -> Unit):
    RecyclerView.Adapter<SuperheroViewHolder>() {

    fun updateList(superheroList: List<Superhero>){
        this.superheroList = superheroList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false))
    }

    override fun getItemCount() = superheroList.size

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.bind(superheroList[position], onItemSelected)
    }

}