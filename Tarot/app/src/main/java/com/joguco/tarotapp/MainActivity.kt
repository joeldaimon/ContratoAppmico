package com.joguco.tarotapp

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.joguco.tarotapp.Interface.OnItemClick
import com.joguco.tarotapp.Model.Carta
import com.joguco.tarotapp.Model.ListFragment
import com.joguco.tarotapp.databinding.ActivityMainBinding
import com.joguco.tarotapp.databinding.ListItemBinding


class MainActivity : AppCompatActivity(), OnItemClick {
    //Atributes
    private lateinit var binding: ActivityMainBinding
    private var lista = ArrayList<Carta>()
    private var mostrarDatos = 0

    private val layoutList: FrameLayout by lazy { findViewById(R.id.containerList) } //List fragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Load ListFragment
        loadRecyclerView()

    }

    //Function that loads RecyclerView
    private fun loadRecyclerView() {
        supportFragmentManager.beginTransaction()
            .replace(layoutList.id, ListFragment.newInstance(6))
            .addToBackStack(null)
            .commit()
    }

    private fun addCarta(carta: Carta){
        lista.add(carta)
        mostrarDatos++
        if(mostrarDatos == 3)(mostrarDatos())
    }

    private fun mostrarDatos(){
        var id = getImage(lista[0].imagen)
        binding.ivOne.setImageResource(id)
        id = getImage(lista[1].imagen)
        binding.ivTwo.setImageResource(id)
        id = getImage(lista[2].imagen)
        binding.ivThree.setImageResource(id)

        binding.nombres.text = lista[0].nombre+" "+lista[1].nombre+" "+lista[2].nombre

        binding.datos.text = "Una relación "+lista[0].inicio+" obtendrá "+lista[1].nudo+" y el resultado será "+lista[2].final+"."

        binding.containerList.isVisible = false
    }

    fun getImage(image: String): Int {
        val imageName = image.split(".")[0]
        val resId = resources.getIdentifier(
            imageName,
            "drawable",
            applicationInfo.packageName
        )

        return resId
    }

    //Function that loads DetailFragment if you Click a Card
    override fun onItemClick(carta: Carta) {
        addCarta(carta)
        binding.tvTestTitle.text = carta.nombre
    }
}