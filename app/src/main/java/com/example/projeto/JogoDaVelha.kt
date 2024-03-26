package com.example.projeto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class JogoDaVelha : AppCompatActivity() {

    var isPlayer1 = true
    var gameEnd = false

    private lateinit var topo: ImageView
    private lateinit var topoStart: ImageView
    private lateinit var topoEnd: ImageView

    private lateinit var center: ImageView
    private lateinit var centerStart: ImageView
    private lateinit var centerEnd: ImageView

    private lateinit var baixo: ImageView
    private lateinit var baixoStart: ImageView
    private lateinit var baixoEnd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jogo_da_velha)


        topo = findViewById(R.id.topo)
        topoStart = findViewById(R.id.topoStart)
        topoEnd =findViewById(R.id.topoEnd)

        center = findViewById(R.id.center)
        centerStart = findViewById(R.id.centerStart)
        centerEnd =findViewById(R.id.centerEnd)

        baixo = findViewById(R.id.baixo)
        baixoStart = findViewById(R.id.baixoStart)
        baixoEnd =findViewById(R.id.baixoEnd)

        val reset: Button = findViewById(R.id.buttom_reset)
        reset.setOnClickListener {
            resetBox(topo)
            resetBox(topoStart)
            resetBox(topoEnd)

            resetBox(center)
            resetBox(centerStart)
            resetBox(centerEnd)

            resetBox(baixo)
            resetBox(baixoStart)
            resetBox(baixoEnd)
        }

        configureBox(topo)
        configureBox(topoStart)
        configureBox(topoEnd)

        configureBox(center)
        configureBox(centerStart)
        configureBox(centerEnd)

        configureBox(baixo)
        configureBox(baixoStart)
        configureBox(baixoEnd)
    }

    private fun resetBox(box: ImageView){
        box.setImageDrawable(null)
        box.tag = null
        isPlayer1 = true
        gameEnd = false
    }

    private fun configureBox(box: ImageView){
        box.setOnClickListener {
            if (box.tag == null && !gameEnd){
                if (isPlayer1) {
                    box.setImageResource(R.drawable.baseline_add_circle_outline_24)
                    isPlayer1 = false
                    box.tag = 1
                }   else {
                    box.setImageResource(R.drawable.baseline_close_24)
                    isPlayer1 = true
                    box.tag = 2
                }

                if (playerWin(1)){
                    Toast.makeText(this@JogoDaVelha, "Player 1 Venceu!", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                } else if (playerWin(2)){
                    Toast.makeText(this@JogoDaVelha, "Player 2 Venceu", Toast.LENGTH_SHORT).show()
                    gameEnd = true
                }
            }
        }
    }

    private fun playerWin(value: Int): Boolean {
        if ( (topo.tag == value && center.tag == value && baixo.tag == value) ||
            (topoStart.tag == value && centerStart.tag == value && baixoStart.tag == value)||
            (topoEnd.tag == value && centerEnd.tag == value && baixoEnd.tag == value)||

            (topoStart.tag == value && topo.tag == value && topoEnd.tag == value)||
            (centerStart.tag == value && center.tag == value && centerEnd.tag == value)||
            (baixoStart.tag == value && baixo.tag == value && baixoEnd.tag == value)||

            (topoStart.tag == value && center.tag == value && baixoEnd.tag == value)||
            (topoEnd.tag == value && center.tag == value && baixoStart.tag == value))
        {
            return true
        }
        return false
    }
}