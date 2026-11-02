package com.example.medicioncloroapp.data.model

data class Alerta(
   val id : Int,
    val puntoId: String,
    val NivelCloro: Double,
    val tipo: String,
    val gravedad: String,
    val fecha : String,
    var resuelta : Boolean = false


)