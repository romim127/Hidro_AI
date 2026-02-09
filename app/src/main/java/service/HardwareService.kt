/* package service

package com.example.hidroai_app.service

object HardwareService {
    // Dentro de HardwareService.kt
    fun procesarReporteIsrael(data: String): String {
        // La placa manda un código, nosotros lo traducimos para el "pajero" del usuario
        return when(data) {
            "Z1_OPEN" -> "IA: ¡Ojo! El tanque se está vaciando en Marte."
            "Z1_CLOSED" -> "IA: Tanque lleno. Cortando flujo."
            "PGM_ON" -> "IA: Bomba Zeta 4 activa. Presión nominal."
            else -> "IA: Recibiendo data satelital..."
        }
    }
}

    // Configuraciones precargadas para que el usuario no configure nada
    private val marcas = mapOf(
        "SONOFF_R4"     to "zeroconf/switch",
        "SHELLY_1"      to "relay/0?turn=on",
        "HIDRO_BOARD"   to "api/v1/pump/start",
        "CHINA_PORONGA" to "control?cmd=GPIO,12,1"
        "RELE_PORTON"   to "relay/0?turn=on", // La ruta que use tu placa china
        )
    )

    fun obtenerPath(marca: String): String {
        return marcas[marca] ?: "control"
    }

    // Dentro de HardwareService.kt
    fun activarBombaGSM() {
        val numeroBomba = "+5492613868237" // El número del chip que le pongas al RTU
        // La App manda un SMS con la clave (por defecto es 1234)
        mandarSMS(numeroBomba, "1234ON")
    }

    // LA INTELIGENCIA: Aquí decidimos si el "pajero" puede gastar agua o no
    // El -1 significa que el sensor no está o no se leyó
    fun evaluarCarga(nivelTanque: Int, humedadSuelo: Int = -1, lluviaProb: Int): Pair<Boolean, String> {

        // 1. Check de Clima (Siempre obligatorio porque es por Internet)
        if (lluviaProb > 70) {
            return Pair(false, "IA: Va a llover pronto. Guardá el agua, no seas animal.")
        }

        // 2. Check de Tanque (Crítico para no reventar la bomba)
        if (nivelTanque > 90) {
            return Pair(false, "IA: El tanque está lleno. ¿Querés inundar la sala de carga?")
        }

        // 3. Check de Humedad (OPCIONAL: Solo si el sensor devolvió algo distinto a -1)
        if (humedadSuelo != -1 && humedadSuelo > 80) {
            return Pair(false, "IA: La tierra ya tiene agua. El sensor dice que no hace falta.")
        }

        // Así quedaría tu lista de mandos para la placa de 4G
        private val comandos4G = mapOf(
            "ABRIR_SOLENOIDE" to "relay/0?turn=on",
            "ARRANCAR_BOMBA"  to "relay/1?turn=on",
            "APAGAR_TODO"     to "relay/all?turn=off"
        )

        // Si llegamos acá, o el suelo está seco o NO hay sensor, así que cargamos igual
        return Pair(true, "IA: Check de seguridad OK. Arrancando sistema...")
    }
 */