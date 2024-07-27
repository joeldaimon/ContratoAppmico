package com.joguco.segundocursokotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joguco.segundocursokotlin.SuperheroApp.SuperheroListActivity
import com.joguco.segundocursokotlin.databinding.ActivityMainBinding
import com.joguco.segundocursokotlin.databinding.ActivitySuperheroListBinding
import com.joguco.segundocursokotlin.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSuperheroApp.setOnClickListener(){
            val intent = Intent(this, SuperheroListActivity::class.java)
            startActivity(intent)
        }

        binding.btnSettings.setOnClickListener(){
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}