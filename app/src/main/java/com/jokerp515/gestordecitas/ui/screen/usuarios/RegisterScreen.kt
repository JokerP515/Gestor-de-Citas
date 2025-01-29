package com.jokerp515.gestordecitas.ui.screen.usuarios

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.jokerp515.gestordecitas.R
import com.jokerp515.gestordecitas.ui.events.UserEvent
import com.jokerp515.gestordecitas.ui.navigation.routes.Routes
import com.jokerp515.gestordecitas.viewmodel.usuarios.RegisterViewModel

@Composable
fun RegisterScreen(viewModel: RegisterViewModel, go: (Any) -> Unit = {}) {

    var idInput by rememberSaveable { mutableStateOf("") }
    var phoneInput by rememberSaveable { mutableStateOf("") }

    // Dado el caso el dispositivo sea pequeño, se puede hacer scroll
    val scrollState = rememberScrollState()

    var errorMessages by rememberSaveable { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(scrollState)
            .padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Text(
            stringResource(R.string.registro_de_usuario_nuevo),
            modifier = Modifier.padding(bottom = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text(stringResource(R.string.nombre)) },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors()
        )
        OutlinedTextField(
            value = idInput, //viewModel.id
            onValueChange = { input ->
                if (input.all { char -> char.isDigit() }) {
                    idInput = input
                }
            },
            label = { Text(stringResource(R.string.identificacion)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text(stringResource(R.string.correo_electronico_opcional)) },
            modifier = Modifier.fillMaxWidth(),
            colors = textFieldColors()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Mensaje de error dinámico
        if (errorMessages.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = stringResource(R.string.falta_la_siguiente_informacion),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.error
                )
                errorMessages.forEach { error ->
                    Text(
                        text = "- $error",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
        //viewModel.saveUser()
        Button(
            onClick = {
                val missingFields = checkMissingFields(
                    viewModel.name,
                    idInput,
                    phoneInput
                )
                if (missingFields.isEmpty()) {
                    // Registrar el gato si están correctos los datos
                    viewModel.id = idInput.toLongOrNull() ?: 0L // Actualizar la edad porque no se hace directamente en el input
                    viewModel.phone = phoneInput.toLongOrNull() ?: 0L // Igual en este campo
                    idInput = ""
                    phoneInput = ""

                    viewModel.onEvent(UserEvent.onSave)
                    errorMessages = emptyList() // Limpiar mensajes de error
                    go(Routes.MenuPrincipal)
                } else {
                    // Mostrar los campos faltantes
                    errorMessages = missingFields
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = stringResource(R.string.guardar),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

fun checkMissingFields(name: String, id: String, phone: String): List<String> {
    val missing = mutableListOf<String>()
    if (name.isBlank()) missing.add("Nombre")
    if (id.isBlank()) missing.add("Identificación")
    if (phone.isBlank()) missing.add("Teléfono")
    return missing
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun textFieldColors() = outlinedTextFieldColors(
    focusedBorderColor = MaterialTheme.colorScheme.primary,
    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
    focusedLabelColor = MaterialTheme.colorScheme.primary,
    cursorColor = MaterialTheme.colorScheme.primary
)