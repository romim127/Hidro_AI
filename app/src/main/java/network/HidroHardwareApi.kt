/* package network

package com.example.hidroai_app.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface HidroHardwareApi {
    // El "path" es lo que cambia entre un Sonoff y una placa china
    @POST("{path}")
    suspend fun enviarComando(
        @Path(value = "path", encoded = true) path: String,
        @Body body: Map<String, String>
    ): Response<ResponseBody>
}
package com.example.hidroai_app.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface HidroHardwareApi {
    // Este método es el que va a "disparar" la orden a la placa
    @POST("{path}")
    suspend fun enviarComando(
        @Path(value = "path", encoded = true) path: String,
        @Body body: Map<String, String>
    ): Response<ResponseBody>
}
/*
/* package com.example.hidroai_app.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

// ARQUITECTURA DE RED - RESERVADO PARA V1.1 (SONOFF / API EXTERNAS)
interface HidroHardwareApi {
    // El "path" es lo que cambia entre un Sonoff y una placa china
    @POST("{path}")
    suspend fun enviarComando(
        @Path(value = "path", encoded = true) path: String,
        @Body body: Map<String, String>
    ): Response<ResponseBody>
}
*/