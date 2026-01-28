package com.example.hidroai_app

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity // Cambiamos a esta para máxima compatibilidad

class MainActivity : ComponentActivity() { // Heredamos de ComponentActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.pbWaterFlow)
        val tvLiters = findViewById<TextView>(R.id.tvLitersCount)
        val tvStatus = findViewById<TextView>(R.id.tvStatusHardware)

        // Iniciamos la animación de la Bomba Zeta 4 (150L/min -> 12.5L en 5s)
        startWaterFlow(progressBar, tvLiters, tvStatus)
    }

    private fun startWaterFlow(pb: ProgressBar, tv: TextView, status: TextView) {
        status.text = "ESTADO: BOMBA ZETA 4 ACTIVA"

        val animator = ValueAnimator.ofFloat(0f, 12.5f)
        animator.duration = 5000
        animator.interpolator = LinearInterpolator()

        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Float
            pb.progress = ((animatedValue / 12.5f) * 100).toInt()
            tv.text = String.format("%.1f", animatedValue)
        }

        animator.start()
    }
}