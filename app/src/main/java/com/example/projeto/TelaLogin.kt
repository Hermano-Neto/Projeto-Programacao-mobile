package com.example.projeto

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class TelaLogin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_login)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#FF000000")

        val loginButton = findViewById<Button>(R.id.loginButton)
        loginButton.setOnClickListener {
            // Aqui você pode iniciar uma nova atividade ou executar qualquer ação desejada
            // Por exemplo, iniciar uma nova atividade após clicar no botão de login
            val intent = Intent(this, Calculadora::class.java)
            startActivity(intent)
        }
    }
}
