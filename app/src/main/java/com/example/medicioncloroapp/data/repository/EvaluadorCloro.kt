package com.example.medicioncloroapp.data.repository

import com.example.medicioncloroapp.data.model.Alerta
import com.example.medicioncloroapp.data.model.PuntoMedicion

object EvaluadorCloro {
    fun evaluarEstado(cloro: Double): String{
        return when{
            cloro >= 3.0 && cloro <=5.0 -> "NORMAL"
            cloro >= 2.0 && cloro <= 6.0 -> "ADVERTENCIA"
            else-> "CRITICO"
        }

    }
    fun crearAlertaCorrespondiente(
        punto: PuntoMedicion
    ): Alerta? {
        val estado = evaluarEstado(punto.nivelCloro)
        if (estado == "NORMAL"){
            return null
        }
        val tipo = if (punto.nivelCloro < 3.0){
            "CLORO BAJO"
        }else {
            "CLORO ALTO"
        }
        return Alerta(
            id = (1000..9999).random(),
            puntoId = punto.id,
            NivelCloro = punto.nivelCloro,
            tipo = tipo,
            gravedad = estado,
            fecha = punto.fechaHora,
            resuelta = false


        )
    }

}