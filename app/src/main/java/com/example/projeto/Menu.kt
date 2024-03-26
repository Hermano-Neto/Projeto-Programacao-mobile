package com.example.projeto

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class Menu : AppCompatActivity() {
    @SuppressLint("WrongViewCast", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#FF000000")

        val imageViewCalculator = findViewById<ImageView>(R.id.imageViewCalculadora)
        imageViewCalculator.setOnClickListener {
            val intent = Intent(this, Calculadora::class.java)
            startActivity(intent)
        }

        val imageViewJogodaVelha = findViewById<ImageView>(R.id.imageViewJogodaVelha)
        imageViewJogodaVelha.setOnClickListener {
            val intent = Intent(this, JogoDaVelha::class.java)
            startActivity(intent)
        }
    }
}
