package com.example.medicioncloroapp.data.repository

import com.example.medicioncloroapp.data.model.Accion
import com.example.medicioncloroapp.data.model.Alerta
import com.example.medicioncloroapp.data.model.PuntoMedicion
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

class CloroRepository {

    private val puntos = mutableListOf<PuntoMedicion>()
    private val alertas = mutableListOf<Alerta>()
    private val acciones = mutableListOf<Accion>()

    init {
        inicializarDatosSimulados()
    }

    private fun inicializarDatosSimulados() {
        val granjas = listOf("Granja Melipilla", "Granja San Pedro", "Granja El Monte")
        var contador = 1
        val fechaActual = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())

        for (g in granjas) {
            for (i in 1..4) {
                // Genera un decimal Double válido entre 1.5 y 6.5
                val cloroInicial: Double = Random.nextInt(15, 66) / 10.0
                val estado = EvaluadorCloro.evaluarEstado(cloroInicial)

                val p = PuntoMedicion(
                    id = "PUNTO-$contador",
                    Granja = g,
                    Galpon = "Galpón $i",
                    Ubicacion = "Bebedero $i",
                    nivelCloro = cloroInicial,
                    fechaHora = fechaActual,
                    estado = estado
                )
                puntos.add(p)

                val alerta = EvaluadorCloro.crearAlertaCorrespondiente(p)
                if (alerta != null) {
                    alertas.add(alerta)
                }
                contador++
            }
        }
    }

    fun obtenerPuntos(): List<PuntoMedicion> = puntos

    fun obtenerAlertasActivas(): List<Alerta> = alertas.filter { !it.resuelta }

    fun registrarAccionCorrectiva(alertaId: Int, descripcion: String, usuario: String) {
        val fecha = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
        val accion = Accion(
            id = Random.nextInt(100, 1000),
            alertaId = alertaId,
            descripcion = descripcion,
            responsable = usuario,
            fechaHora = fecha
        )
        acciones.add(accion)
        alertas.find { it.id == alertaId }?.resuelta = true
    }

    fun actualizarMedicionesSimuladas() {
        val fecha = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(Date())
        for (p in puntos) {
            val nuevoCloro: Double = Random.nextInt(20, 56) / 10.0
            p.nivelCloro = nuevoCloro
            p.estado = EvaluadorCloro.evaluarEstado(nuevoCloro)
            p.fechaHora = fecha

            val nuevaAlerta = EvaluadorCloro.crearAlertaCorrespondiente(p)
            if (nuevaAlerta != null) {
                alertas.add(nuevaAlerta)
            }
        }
    }
}