package com.example.medicioncloroapp.ui.auth


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreen(
    onLoginSuccess:()-> Unit
) {
    var correo by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorCorreo by remember { mutableStateOf(false) }
    var errorPassword by remember { mutableStateOf(false) }
    var errorLogin by remember { mutableStateOf(false) }
    val context = androidx.compose.ui.platform.LocalContext.current
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text(
            text = "Medicion de Cloro",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("Inicia sesion para continuar")

        Spacer(modifier = Modifier.height(32.dp))


        OutlinedTextField(
            value = correo,
            onValueChange = { nuevoCorreo ->
                correo = nuevoCorreo
                errorCorreo = false
                errorLogin = false
            },
            label = { Text("Correo electrónico") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Correo"
                )
            },
            isError = errorCorreo,
            supportingText = {
                if (errorCorreo) {
                    Text("Ingresa un correo válido")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )


        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { nuevaPassword ->
                password = nuevaPassword
                errorPassword = false
                errorLogin = false

            },
            label = { Text("Contraseña") },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = null)
            },
            visualTransformation = PasswordVisualTransformation(),
            isError = errorPassword,
            supportingText = {
                if (errorPassword) {
                    Text("La contraseña debe tener al menos 6 caracteres")
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()

        )

        if (errorLogin) {
            Text(text = "Correo o contraeña incorrecta", color = MaterialTheme.colorScheme.error)
        }
        Spacer(modifier = Modifier.height(20.dp))



        Button(
            onClick = {
                val correoLimpio = correo.trim()
                val passwordLimpia = password.trim()

                errorCorreo = correoLimpio.isEmpty() ||
                        !android.util.Patterns.EMAIL_ADDRESS
                            .matcher(correoLimpio).matches()

                errorPassword = passwordLimpia.length < 6
                errorLogin = false

                if (!errorCorreo && !errorPassword) {
                    if (
                        correoLimpio.equals(
                            "admin@cloro.cl",
                            ignoreCase = true
                        ) &&
                        passwordLimpia == "123456"
                    ) {
                        onLoginSuccess()
                    } else {
                        errorLogin = true
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar sesión")
        }
    }
}
