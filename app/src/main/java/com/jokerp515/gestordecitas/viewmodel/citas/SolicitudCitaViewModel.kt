package com.jokerp515.gestordecitas.viewmodel.citas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokerp515.gestordecitas.data.citas.Especialidad
import com.jokerp515.gestordecitas.local.entities.CitaEntity
import com.jokerp515.gestordecitas.local.repositories.CitaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SolicitudCitaViewModel @Inject constructor(
    private val citaRepository: CitaRepository
) : ViewModel() {
    fun solicitarCita(especialidad: Especialidad, dia: String, hora: String, userId: Long) {
        viewModelScope.launch {
            val cita = CitaEntity(
                especialidad = especialidad.name,
                dia = dia,
                hora = hora,
                userId = userId
            )
            citaRepository.insertarCita(cita)
        }
    }
}
