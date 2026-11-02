package com.example.medicioncloroapp.data.model



data class PuntoMedicion(
    val id: String,
    val Granja: String,
    val Galpon: String,
    val Ubicacion: String,
    var nivelCloro: Double,
    var fechaHora: String,
    var estado: String
)