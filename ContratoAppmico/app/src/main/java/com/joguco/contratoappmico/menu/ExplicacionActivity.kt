package com.joguco.contratoappmico.menu

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joguco.contratoappmico.R
import com.joguco.contratoappmico.databinding.ActivityExplicacionBinding
import com.joguco.contratoappmico.databinding.ActivityMainBinding

class ExplicacionActivity : AppCompatActivity() {
    //Atributos
    private lateinit var binding: ActivityExplicacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicacion)

        //Setting binding
        binding = ActivityExplicacionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        InitListeners()
    }

    private fun InitListeners() {
        binding.gematrix.setOnClickListener{
            try{
                val uri = Uri.parse("https://www.gematrix.org")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            catch(e : ActivityNotFoundException){
            }
        }

        binding.billheidrick.setOnClickListener{
            try{
                val uri = Uri.parse("https://www.billheidrick.com/works/hgm1.htm")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }
            catch(e : ActivityNotFoundException){
            }
        }
    }
}