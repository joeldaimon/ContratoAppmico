package com.joguco.tarotapp.Model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.joguco.tarotapp.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

data class Carta(
    val id: Int,
    val nombre:String,
    val etiquetas: Array<String>,
    val inicio: String,
    val nudo: String,
    val final: String,
    val respuesta: String,
    val imagen: String){


    //Function toString
    override fun toString(): String {
        return "($this.id) $this.name"
    }

    //Static object
    companion object{
        //We create a List of Series
        val Mazo:MutableList<Carta> = mutableListOf()

        /*
        * Function that loads the Cartas
        * Returns a MutableList<Carta>
         */
        fun loadSeries(context: Context):MutableList<Carta>{
            //We get the context of the data file - datos_json
            val raw = context.resources.openRawResource(R.raw.mazo)

            //Reader of data
            val rd = BufferedReader(InputStreamReader(raw))

            //Type of list we are going to use ot save the data
            val listType: Type = object : TypeToken<MutableList<Carta?>?>() {}.type

            //We create a gson Object
            val gson = Gson()
            Mazo.clear()

            //Saving data from json to variable Series
            var cartas:List<Carta> = gson.fromJson(rd, listType)

            cartas = cartas.shuffled()

            //Saving series data to original MutableList - serieList
            Mazo.addAll(cartas)


            //Returning
            return Mazo

        }

        /*
        * Function that returns a Serie by its ID
         */
        fun getCartaById(id:Int?): Carta?{
            val carta = Mazo.filter{
                it.id == id
            }

            return if(carta.isNotEmpty()) carta[0] else null
        }

    }
}
