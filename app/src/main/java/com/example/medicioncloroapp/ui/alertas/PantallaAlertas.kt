package com.example.medicioncloroapp.ui.alertas



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.medicioncloroapp.data.model.Alerta
import com.example.medicioncloroapp.data.repository.CloroRepository
import com.example.medicioncloroapp.data.model.PuntoMedicion



@Composable
fun PantallaAlertas(repo: CloroRepository){

    val alertas = repo.obtenerAlertasActivas()
    val puntos = repo.obtenerPuntos()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)

    ){
        Text(text = "Alertas de Cloro",
        style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(16.dp))

        if (alertas.isEmpty()){
            Text("No hay alertas activas")
        }else {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(alertas) { alerta ->

                    val punto = puntos.find {
                        it.id == alerta.puntoId
                    }

                    TarjetaAlerta(
                        alerta = alerta,
                        punto = punto
                    )
                }
            }
        }

        }
    }

@Composable
fun TarjetaAlerta(
    alerta: Alerta,
    punto: PuntoMedicion?
) {
    val esCritica = alerta.gravedad == "CRITICO"

    val colorFondo = if (esCritica) {
        Color(0xFFFFCDD2)
    } else {
        Color(0xFFFFE0B2)
    }

    val colorTexto = if (esCritica) {
        Color(0xFFB71C1C)
    } else {
        Color(0xFFE65100)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = colorFondo,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = alerta.tipo,
            style = MaterialTheme.typography.titleMedium,
            color = colorTexto
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Granja: ${punto?.Granja ?: "No disponible"}"
        )

        Text(
            text = "Galpón: ${punto?.Galpon ?: "No disponible"}"
        )

        Text(
            text = "Ubicación: ${punto?.Ubicacion ?: "No disponible"}"
        )

        Text("Código: ${alerta.puntoId}")

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Cloro libre: ${String.format(java.util.Locale.getDefault(), "%.2f", alerta.NivelCloro)} ppm"
        )

        Text(
            text = "Estado: ${alerta.gravedad}",
            color = colorTexto
        )

        Text("Fecha: ${alerta.fecha}")
    }
}




