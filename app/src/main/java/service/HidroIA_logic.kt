/* package service

package com.example.hidroai_app.service

object HidroIA_Logic {
    // Esta función decide si usamos el Wi-Fi o el módulo de Israel
    fun ejecutarCargaMaqueta(hayWifi: Boolean): String {
        return if (hayWifi) {
            "control/wifi/pump_on"
        } else {
            "israel/gsm/trigger_grey_cable" // Aquí usamos el chip Claro
        }
    }
}
/*
package com.example.hidroai_app.service

object HidroIA_Logic {
    // Esta función decide si usamos el Wi-Fi o el módulo de Israel
    fun ejecutarCargaMaqueta(hayWifi: Boolean): String {
        return if (hayWifi) {
            "control/wifi/pump_on"
        } else {
            "israel/gsm/trigger_grey_cable" // Aquí usamos el chip Claro
        }
    }
}
*/