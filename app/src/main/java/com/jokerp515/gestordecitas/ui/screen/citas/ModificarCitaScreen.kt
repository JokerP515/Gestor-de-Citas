package com.jokerp515.gestordecitas.ui.screen.citas

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jokerp515.gestordecitas.R
import com.jokerp515.gestordecitas.local.entities.CitaEntity
import com.jokerp515.gestordecitas.ui.navigation.routes.Routes
import com.jokerp515.gestordecitas.ui.screen.BackButton
import com.jokerp515.gestordecitas.viewmodel.citas.ModificarCitaViewModel

@Composable
fun ModificarCitaScreen(
    citaId: Long,
    viewModel: ModificarCitaViewModel,
    go: (Any) -> Unit
) {
    // Estado para la cita cargada
    var cita by remember { mutableStateOf<CitaEntity?>(null) }

    // Efecto para cargar la cita desde el ViewModel
    LaunchedEffect(citaId) {
        cita = viewModel.getCitaById(citaId)
    }

    // Variables para los datos modificables de la cita
    val (nuevoDia, setNuevoDia) = rememberSaveable { mutableStateOf("") }
    val (nuevaHora, setNuevaHora) = rememberSaveable { mutableStateOf("") }

    // Sincroniza los valores iniciales solo cuando la cita ya esté cargada
    LaunchedEffect(cita) {
        cita?.let {
            setNuevoDia(it.dia)
            setNuevaHora(it.hora)
        }
    }

    // Evitar renderizado si la cita aún no está cargada
    if (cita != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            BackButton(
                go = go,
                route = Routes.ListaCitas,
                title = stringResource(R.string.modificar_cita)
            )

            DatePickerField(
                selectedDate = nuevoDia,
                onDateSelected = setNuevoDia
            )

            Spacer(modifier = Modifier.height(16.dp))

            TimePickerField(
                selectedTime = nuevaHora,
                onTimeSelected = setNuevaHora
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    viewModel.modifyCita(cita!!.id, nuevoDia, nuevaHora)
                    go(Routes.ListaCitas)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(stringResource(R.string.guardar_cambios))
            }
        }
    } else {
        // Mostrar un loader o mensaje mientras se carga la cita
        Text(text = "Cargando cita...")
    }
}
