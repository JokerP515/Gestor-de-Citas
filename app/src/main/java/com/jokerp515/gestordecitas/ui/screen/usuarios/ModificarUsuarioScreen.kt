package com.jokerp515.gestordecitas.ui.screen.usuarios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jokerp515.gestordecitas.R
import com.jokerp515.gestordecitas.local.entities.UserEntity
import com.jokerp515.gestordecitas.ui.navigation.routes.Routes
import com.jokerp515.gestordecitas.ui.screen.BackButton
import com.jokerp515.gestordecitas.viewmodel.usuarios.ModificarUsuarioViewModel

@Composable
fun ModificarUsuarioScreen(
    viewModel: ModificarUsuarioViewModel,
    go: (Any) -> Unit
) {
    var usuario by remember { mutableStateOf<UserEntity?>(null) }

    LaunchedEffect(Unit) {
        usuario = viewModel.getUser()
    }

    if (usuario != null) {
        var nameInput by rememberSaveable { mutableStateOf(usuario!!.name) }
        var phoneInput by rememberSaveable { mutableStateOf(usuario!!.phone.toString()) }
        var emailInput by rememberSaveable { mutableStateOf(usuario!!.email ?: "") }

        // Dado el caso el dispositivo sea pequeño, se puede hacer scroll
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            BackButton(
                go = go,
                route = Routes.MenuPrincipal,
                title = stringResource(R.string.modificar_tus_datos)
            )

            OutlinedTextField(
                value = nameInput,
                onValueChange = { nameInput = it },
                label = { Text(stringResource(R.string.nombre)) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors()
            )
            OutlinedTextField(
                value = phoneInput, //viewModel.phone
                onValueChange = { input ->
                    if (input.all { char -> char.isDigit() }) {
                        phoneInput = input
                    }
                },
                label = { Text(stringResource(R.string.telefono)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = textFieldColors()
            )
            OutlinedTextField(
                value = emailInput,
                onValueChange = { emailInput = it },
                label = { Text(stringResource(R.string.correo_electronico)) },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.modifyUser(
                        id = usuario!!.id,
                        newName = nameInput,
                        newPhone = phoneInput.toLongOrNull() ?: 0L,
                        newEmail = emailInput
                    )
                    go(Routes.MenuPrincipal)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(
                    text = stringResource(R.string.guardar_cambios),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}