package com.jokerp515.gestordecitas.ui.screen.citas

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jokerp515.gestordecitas.R
import com.jokerp515.gestordecitas.viewmodel.citas.SolicitudCitaViewModel
import com.jokerp515.gestordecitas.data.citas.Especialidad
import com.jokerp515.gestordecitas.ui.navigation.routes.Routes
import com.jokerp515.gestordecitas.ui.screen.BackButton
import java.util.Calendar

@Composable
fun SolicitudCitaScreen(
    viewModel: SolicitudCitaViewModel,
    go: (Any) -> Unit
) {
    val especialidades = Especialidad.entries
    var selectedEspecialidad by rememberSaveable { mutableStateOf(especialidades.first()) }
    var fecha by rememberSaveable { mutableStateOf("") }
    var hora by rememberSaveable { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        BackButton(go = go, route = Routes.MenuPrincipal, title = "")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.solicitar_cita_medica),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Seleccionar especialidad
            EspecialidadDropdown(
                selectedEspecialidad = selectedEspecialidad,
                onEspecialidadSelected = { selectedEspecialidad = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Selector de fecha
            DatePickerField(
                selectedDate = fecha,
                onDateSelected = { fecha = it }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Selector de hora
            TimePickerField(
                selectedTime = hora,
                onTimeSelected = { hora = it }
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.solicitarCita(selectedEspecialidad, fecha, hora, 1)
                    go(Routes.MenuPrincipal)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(stringResource(R.string.solicitar_cita))
            }
        }
    }
}


@Composable
fun EspecialidadDropdown(
    selectedEspecialidad: Especialidad?,
    onEspecialidadSelected: (Especialidad) -> Unit
) {
    val especialidades = Especialidad.entries
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedEspecialidad?.name ?: stringResource(R.string.seleccionar_especialidad),
            onValueChange = { },
            label = { Text(stringResource(R.string.especialidad), color = MaterialTheme.colorScheme.onBackground) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { expanded = true }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Abrir menú",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            especialidades.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.name) },
                    onClick = {
                        onEspecialidadSelected(item)
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun DatePickerField(
    selectedDate: String,
    onDateSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    OutlinedTextField(
        value = selectedDate.ifEmpty { stringResource(R.string.seleccionar_fecha) },
        onValueChange = { },
        label = { Text(stringResource(R.string.fecha)) },
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = {
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        val date = "$year-${(month + 1).toString().padStart(2, '0')}-${
                            dayOfMonth.toString().padStart(2, '0')
                        }"
                        onDateSelected(date)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                datePickerDialog.show()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = stringResource(R.string.seleccionar_fecha)
                )
            }
        }
    )
}

@Composable
fun TimePickerField(
    selectedTime: String,
    onTimeSelected: (String) -> Unit
) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    OutlinedTextField(
        value = selectedTime.ifEmpty { stringResource(R.string.seleccionar_hora) },
        onValueChange = { },
        label = { Text("Hora") },
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        trailingIcon = {
            IconButton(onClick = {
                val timePickerDialog = TimePickerDialog(
                    context,
                    { _, hourOfDay, minute ->
                        val time = "${hourOfDay.toString().padStart(2, '0')}:${minute.toString().padStart(2, '0')}"
                        onTimeSelected(time)
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true // Formato 24 horas
                )
                timePickerDialog.show()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = stringResource(R.string.seleccionar_hora)
                )
            }
        }
    )
}
