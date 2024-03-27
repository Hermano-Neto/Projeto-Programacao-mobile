package com.example.projeto

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.util.Stack

class Calculadora : AppCompatActivity() {

    private lateinit var editTextValor: EditText

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora)

        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#FF000000")

        editTextValor = findViewById(R.id.editTextValor)
    }

    fun onClickVoltar(view: View) {
        finish()
    }

    fun onClickSair(view: View) {
        val intent = Intent(this, TelaLogin::class.java)
        startActivity(intent)
    }

    fun onClickNumero(view: View) {
        val button = view as Button
        val numero = button.text.toString()
        val textoAtual = editTextValor.text.toString()

        if (textoAtual == "0" && numero == ",") {
            editTextValor.setText("0.")
        } else if (textoAtual == "0") {
            editTextValor.setText(numero)
        } else if (numero == "," && !textoAtual.contains(".")) {
            editTextValor.append(".")
        } else if (numero == "," && !textoAtual.contains(",")) {
            editTextValor.append(".")
        } else if (numero != ",") {
            editTextValor.append(numero)
        }
    }



    fun onClickOperacao(view: View) {
        val button = view as Button
        val operacao = button.text.toString()
        val textoAtual = editTextValor.text.toString()

        if (!textoAtual.endsWith(".")) {
            editTextValor.append(operacao)
        }
    }


    fun onClickLimpar(view: View) {
        editTextValor.setText("")
    }

    fun onClickApagar(view: View) {
        val textoAtual = editTextValor.text.toString()

        if (textoAtual.isNotEmpty()) {
            editTextValor.setText(textoAtual.substring(0, textoAtual.length - 1))
        }
    }

    fun onClickIgual(view: View) {
        val expressao = editTextValor.text.toString().trim()

        if (expressao.isNotEmpty()) {
            val resultado = calcularResultado(expressao)

            val resultadoFormatado = if (resultado % 1 == 0.0) {
                resultado.toInt().toString()
            } else {
                resultado.toString()
            }

            editTextValor.setText(resultadoFormatado)
        }
    }


    private fun calcularResultado(expressao: String): Double {
        val pilhaNumeros = Stack<Double>()
        val pilhaOperadores = Stack<Char>()
        var numeroAtual = StringBuilder()

        for (caractere in expressao) {
            if (Character.isDigit(caractere) || caractere == '.') {
                numeroAtual.append(caractere)
            } else {
                if (numeroAtual.isNotEmpty()) {
                    pilhaNumeros.push(numeroAtual.toString().toDouble())
                    numeroAtual.clear()
                }

                while (!pilhaOperadores.empty() && prioridade(pilhaOperadores.peek()) >= prioridade(caractere)) {
                    calcular(pilhaNumeros, pilhaOperadores)
                }

                pilhaOperadores.push(caractere)
            }
        }

        if (numeroAtual.isNotEmpty()) {
            pilhaNumeros.push(numeroAtual.toString().toDouble())
        }

        while (!pilhaOperadores.empty()) {
            calcular(pilhaNumeros, pilhaOperadores)
        }

        return pilhaNumeros.pop()
    }

    private fun prioridade(operador: Char): Int {
        return when (operador) {
            '+', '-' -> 1
            'X', '/' -> 2
            else -> 0
        }
    }

    private fun calcular(pilhaNumeros: Stack<Double>, pilhaOperadores: Stack<Char>) {
        val operador = pilhaOperadores.pop()
        val direita = pilhaNumeros.pop()
        val esquerda = pilhaNumeros.pop()

        when (operador) {
            '+' -> pilhaNumeros.push(esquerda + direita)
            '-' -> pilhaNumeros.push(esquerda - direita)
            'X' -> pilhaNumeros.push(esquerda * direita)
            '/' -> pilhaNumeros.push(esquerda / direita)
        }
    }
}
