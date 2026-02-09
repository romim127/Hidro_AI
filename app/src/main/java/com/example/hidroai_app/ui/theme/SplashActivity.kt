package com.example.hidroai_app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.hidroai_app.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen) // Usa el XML que acabamos de arreglar

        // IA: Iniciando temporizador de presentación
        Handler().postDelayed(object : Runnable {
            override fun run() {
                // Saltar al Dashboard principal
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // Cerramos el Splash para que no vuelva atrás al apretar 'atrás'
            }
        }, 3000) // 3 segundos de facha industrial
    }
}