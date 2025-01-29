package com.jokerp515.gestordecitas.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jokerp515.gestordecitas.R
import com.jokerp515.gestordecitas.ui.navigation.routes.Routes
import com.jokerp515.gestordecitas.viewmodel.usuarios.MenuPrincipalViewModel

@Composable
fun MenuPrincipalScreen(
    viewModel: MenuPrincipalViewModel,
    go: (Any) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = viewModel.welcomeMessage,
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(32.dp))

        MenuButton(
            textRes = R.string.solicitar_cita_medica,
            onClick = { go(Routes.RegistroCitas) }
        )
        MenuButton(
            textRes = R.string.ver_citas_medicas_solicitadas,
            onClick = { go(Routes.ListaCitas) }
        )
        MenuButton(
            textRes = R.string.modificar_tus_datos,
            onClick = { go(Routes.ModificarUsuario) }
        )
    }
}

@Composable
fun MenuButton(
    textRes: Int,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary, // Fondo adaptativo del botón
            contentColor = MaterialTheme.colorScheme.onPrimary // Texto del botón adaptativo
        )
    ) {
        Text(
            text = stringResource(id = textRes),
            style = MaterialTheme.typography.bodyLarge // Tamaño del texto del botón
        )
    }
}