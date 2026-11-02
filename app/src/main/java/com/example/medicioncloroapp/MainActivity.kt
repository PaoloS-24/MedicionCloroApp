package com.example.medicioncloroapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.medicioncloroapp.data.repository.CloroRepository
import com.example.medicioncloroapp.ui.auth.LoginScreen
import com.example.medicioncloroapp.ui.dashboard.MainScreen
import com.example.medicioncloroapp.ui.theme.MedicionCloroAppTheme
import com.example.medicioncloroapp.ui.navegacion.NavegacionApp

class MainActivity : ComponentActivity() {
    private val repo = CloroRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MedicionCloroAppTheme{var sesionIniciada by remember {
                mutableStateOf(false)
            }
                if (sesionIniciada){
                    NavegacionApp(repo)
                }else{
                    LoginScreen(
                        onLoginSuccess = {
                            sesionIniciada = true
                        }
                    )

                }

            }
        }
    }
}