package com.joguco.contratoappmico

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.joguco.contratoappmico.databinding.ActivityMainBinding
import com.joguco.contratoappmico.menu.ExplicacionActivity
import kotlin.math.round


class MainActivity : AppCompatActivity() {
    //Atributos
    private lateinit var binding: ActivityMainBinding

    private val ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    private var nombre: String = ""

    //Lista de números
    private var lista: ArrayList<Int> = ArrayList()

    //Numeros
    var karmaf: Int = 0
    var karmae = 0
    var talentof = 0
    var talentoe = 0
    var misionf = 0
    var misione = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setListeners()
    }

    private fun setListeners() {
        binding.btnCalcular.setOnClickListener{ calcular() }
        binding.btnBorrar.setOnClickListener{ borrar() }
    }

    /*
    *   Función que calcula los números a tavés del nombre
     */
    private fun calcular() {
        nombre = binding.etNombre.text.toString()

        if(nombre == ""){
            binding.tvExplicacion.setTextColor(Color.RED)
            binding.tvExplicacion.setText(R.string.error_nombre)
        }else {
            binding.btnCalcular.isEnabled = false
            binding.tvExplicacion.setTextColor(Color.WHITE)
            binding.tvExplicacion.setText(R.string.escribe_nombre)
            binding.lySignificado.isVisible = true

            //Quitamos los espacios al nombre
            nombre = nombre.replace(" ","").uppercase()

            //Recorremos nombre
            for (i in 0 until nombre.length) {
                lista.add(ABC.indexOf(nombre[i]) + 1)
            }

            //Colocamos datos en cada Botón
            try {
                var i = 0 //Contador

                while(true){
                    karmaf += lista[i]
                    i++

                    karmae += lista[i]
                    i++

                    talentof += lista[i]
                    i++

                    talentoe += lista[i]
                    i++

                    misionf += lista[i]
                    i++

                    misione += lista[i]
                    i++

                    if(i == (nombre.length)) break;
                }
            } catch(e: IndexOutOfBoundsException) { }

            //Añadimos las SUMAS y llamamos al método reducir
            binding.btnOne.text = binding.btnOne.text.toString()+"> "+karmaf+"/"+reducir(karmaf)
            binding.btnTwo.text = binding.btnTwo.text.toString()+"> "+karmae+"/"+reducir(karmae)
            binding.btnThree.text = binding.btnThree.text.toString()+"> "+talentof+"/"+reducir(talentof)
            binding.btnFour.text = binding.btnFour.text.toString()+"> "+talentoe+"/"+reducir(talentoe)
            binding.btnFive.text = binding.btnFive.text.toString()+"> "+misionf+"/"+reducir(misionf)
            binding.btnSix.text = binding.btnSix.text.toString()+"> "+misione+"/"+reducir(misione)

        }
    }

    private fun numValido(num: Int): Boolean {
        if(num == 0) return false
        if(num<10) return true
        if(num==11 || num==22 || num==33 || num==44) return true

        return false
    }

    private fun reducir(num: Int): Int {
        var numero:Float = num/10.toFloat()
        var entero = numero.toInt()
        var decimal = round((numero - entero)*10)
        var suma = entero+decimal.toInt()
        if(!numValido(suma)) suma = reducir(suma);

        return suma;
    }

    private fun borrar(){
        //Reseteamos texto
        binding.btnOne.text = "1.KF: "
        binding.btnTwo.text = "2.KE: "
        binding.btnThree.text = "3.TF: "
        binding.btnFour.text = "4.TE: "
        binding.btnFive.text = "5.MF: "
        binding.btnSix.text = "6.ME: "

        //Reseteamos numeros
        karmaf = 0
        karmae = 0
        talentof = 0
        talentoe = 0
        misionf = 0
        misione = 0

        //Borramos lista
        lista.clear()

        //Reseteamos label por si le dimos a borrar al encontrar error
        binding.tvExplicacion.setTextColor(Color.WHITE)
        binding.tvExplicacion.setText(R.string.escribe_nombre)
        binding.etNombre.getText().clear()

        binding.btnCalcular.isEnabled = true
        binding.lySignificado.isVisible = false
    }


    //Settings
    override fun onCreateOptionsMenu (menu: Menu?): Boolean {
        menuInflater.inflate (R.menu.s_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.que_es_action -> {
                startActivity (Intent(this, ExplicacionActivity::class.java))
            }
            R.id.creador_action -> {
                val uri: Uri = Uri.parse("https://www.tiktok.com/@joeldaimon")
                startActivity(Intent(Intent.ACTION_VIEW, uri))
            }
        }
        return super.onOptionsItemSelected(item)
    }


}