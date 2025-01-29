package com.jokerp515.gestordecitas.viewmodel.citas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokerp515.gestordecitas.local.entities.CitaEntity
import com.jokerp515.gestordecitas.local.repositories.CitaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListaCitasViewModel
@Inject constructor(
    private val citaRepository: CitaRepository
) : ViewModel() {

    var citaFlow = citaRepository.obtenerCitas()

    fun eliminarCita(cita: CitaEntity) {
        viewModelScope.launch {
            citaRepository.deleteById(cita.id)
        }
    }

}