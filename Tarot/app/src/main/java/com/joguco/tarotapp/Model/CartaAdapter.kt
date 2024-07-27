package com.joguco.tarotapp.Model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.joguco.tarotapp.Interface.OnItemClick
import com.joguco.tarotapp.databinding.ListItemBinding

class CartaAdapter(
    private val cartaList: List<Carta>,
    private val listener: OnItemClick?
) : RecyclerView.Adapter<CartaAdapter.ViewHolder>() {

    //Creates a ViewHolder for every item of the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,

                false
            )
        )

    }

    //Function that customizes the data depending the position
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Changing data
        val carta = cartaList[position]

        //Tag of the Serie data
        holder.itemView.tag = carta

        holder.itemView.setOnClickListener(holder) //Our ViewHolder implements OnClickListener
    }

    //Function that returns the Size of the serieList
    override fun getItemCount(): Int = cartaList.size


    /*
    * RecyclerView Holder
    *   Inner class
    */
    inner class ViewHolder(binding: ListItemBinding)
        : RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {
        //Loading view of each item on list_item.xml



        //Function that does something when user clicks on an Item
        override fun onClick(v: View?) {

            //Serie by tag
            val carta = v?.tag as Carta

            //Listener
            listener?.onItemClick(carta)
        }
    }
}