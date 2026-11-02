package com.example.medicioncloroapp.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.medicioncloroapp.data.model.PuntoMedicion
import com.example.medicioncloroapp.data.repository.CloroRepository

@Composable
fun MainScreen(repo: CloroRepository) {
    var puntos by remember { mutableStateOf(repo.obtenerPuntos()) }
    var alertas by remember { mutableStateOf(repo.obtenerAlertasActivas()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Monitoreo de Cloro en Línea", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                repo.actualizarMedicionesSimuladas()
                puntos = repo.obtenerPuntos().toList()
                alertas = repo.obtenerAlertasActivas().toList()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Simular Actualización de Datos (API)")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Alertas Activas: ${alertas.size}", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn {
            items(puntos) { punto ->
                PuntoItem(punto = punto)
            }
        }
    }
}

@Composable
fun PuntoItem(punto: PuntoMedicion) {
    val colorEstado = when (punto.estado) {
        "NORMAL" -> Color(0xFF2E7D32)
        "ADVERTENCIA" -> Color(0xFFEF6C00)
        else -> Color(0xFFC62828)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = colorEstado.copy(alpha = 0.12f))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "${punto.Granja} - ${punto.Galpon}", style = MaterialTheme.typography.titleSmall)
            Text(text = "Ubicación: ${punto.Ubicacion}")
            Text(text = "Cloro libre: ${punto.nivelCloro} ppm")
            Text(text = "Estado: ${punto.estado}", color = colorEstado)
            Text(text = "Última act.: ${punto.fechaHora}", style = MaterialTheme.typography.bodySmall)
        }
    }
}