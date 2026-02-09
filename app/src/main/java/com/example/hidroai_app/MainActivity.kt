package com.example.hidroai_app

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {

    // --- BLOQUE DE VARIABLES (Declaración) ---
    private lateinit var tvIA: TextView
    private lateinit var tvStatus: TextView
    private lateinit var btnScan: Button
    private lateinit var container: LinearLayout
    private lateinit var wifiManager: WifiManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // --- VINCULACIÓN CON LA UI (El puente) ---
        tvIA = findViewById(R.id.tvIAMessageText)
        tvStatus = findViewById(R.id.tvStatusHardware)
        btnScan = findViewById(R.id.btnAutoGestion)
        container = findViewById(R.id.main_container_layout)

        wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        // Bienvenida con el Clima
        tvIA.text = "Hello! Soy HidroAI.\nClima: 24°C - Despejado.\nListo para escanear."

        btnScan.setOnClickListener {
            solicitarPermisosYEscanear()
        }
    }

    private fun solicitarPermisosYEscanear() {
        val permisos = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permisos, 123)
        } else {
            escanearWifiReal()
        }
    }

    private fun escanearWifiReal() {
        tvStatus.text = "ESCANEANDO ESPECTRO..."

        val wifiReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val resultados = wifiManager.scanResults
                actualizarLista(resultados)
            }
        }

        // Corrección para Android 14 (Pixel 7)
        val filter = IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(wifiReceiver, filter, Context.RECEIVER_EXPORTED)
        } else {
            registerReceiver(wifiReceiver, filter)
        }

        val success = wifiManager.startScan()
        if (!success) {
            Toast.makeText(this, "Escaneo limitado por el sistema", Toast.LENGTH_SHORT).show()
        }
    }

    private fun actualizarLista(resultados: List<android.net.wifi.ScanResult>) {
        container.removeAllViews()
        tvStatus.text = "SE ENCONTRARON ${resultados.size} PLACAS"

        for (red in resultados) {
            val btnRed = Button(this)
            // Mostramos el nombre de la red (SSID)
            val nombreRed = if (red.SSID.isEmpty()) "[Red Oculta]" else red.SSID
            btnRed.text = "SUSCRIBIRSE A: $nombreRed"

            btnRed.setOnClickListener {
                tvIA.text = "IA: Vinculado a $nombreRed.\nRecibiendo telemetría en tiempo real."
                Toast.makeText(this, "Conectado a $nombreRed", Toast.LENGTH_SHORT).show()
            }
            container.addView(btnRed)
        }
    }
}
