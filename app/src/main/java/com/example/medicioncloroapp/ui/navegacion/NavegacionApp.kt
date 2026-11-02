package com.example.medicioncloroapp.ui.navegacion


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import com.example.medicioncloroapp.data.repository.CloroRepository
import com.example.medicioncloroapp.ui.dashboard.MainScreen
import com.example.medicioncloroapp.ui.alertas.PantallaAlertas

@Composable
fun NavegacionApp(repo: CloroRepository){
    var pantallaActual by remember {
        mutableStateOf("Inicio")
    }
    Scaffold(bottomBar = {
        NavigationBar {
            listOf(
                "Inicio",
                "Mediciones",
                "Alertas",
                "Historial"
            ).forEach { pantalla ->
                NavigationBarItem(
                    selected = pantallaActual == pantalla,
                    onClick = {
                        pantallaActual = pantalla
                    },
                    icon = {
                        Text(
                            when (pantalla){
                                "Inicio"-> "⌂"
                                "Mediciones" -> "◉"
                                "Alertas" -> "!"
                                else -> "◷"

                            }
                        )


                    },
                    label = {
                        Text(pantalla)
                    }

                )
            }


        }
    }

    ) {paddingValues -> Box(modifier = Modifier.padding(paddingValues))
    {
        when (pantallaActual){
            "Inicio" -> MainScreen(repo)
            "Mediciones" -> {
                PantallaTemporal(
                    "Mediciones de cloro"
                )
            }
            "Alertas" -> {
                PantallaAlertas(repo)
            }
            "Historial"-> {
                PantallaTemporal(
                    "Historial de mediciones")
                }
            }
        }

    }


    }
@Composable
private fun PantallaTemporal(titulo:String){
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ){
        Text(
            text = titulo,
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text("Seccion en desarrollo")
    }



}