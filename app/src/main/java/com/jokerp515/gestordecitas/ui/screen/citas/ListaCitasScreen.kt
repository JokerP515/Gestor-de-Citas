package com.jokerp515.gestordecitas.ui.screen.citas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jokerp515.gestordecitas.R
import com.jokerp515.gestordecitas.ui.cards.CitaCard
import com.jokerp515.gestordecitas.ui.navigation.routes.Routes
import com.jokerp515.gestordecitas.ui.screen.BackButton
import com.jokerp515.gestordecitas.viewmodel.citas.ListaCitasViewModel

@Composable
fun ListaCitasScreen(viewModel: ListaCitasViewModel, go: (Any) -> Unit = {}) {
    val listaCitas = viewModel.citaFlow.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        BackButton(go = go, route = Routes.MenuPrincipal, title = stringResource(R.string.lista_de_citas_solicitadas))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(listaCitas.value.size) { index ->
                    val cita = listaCitas.value[index]
                    CitaCard(cita = cita, go = go, onDelete = { viewModel.eliminarCita(cita) })
                }
            }
        }
    }
}