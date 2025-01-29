package com.jokerp515.gestordecitas.viewmodel.citas

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokerp515.gestordecitas.local.repositories.CitaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModificarCitaViewModel @Inject constructor(
    private val citaRepository: CitaRepository
) : ViewModel() {
    suspend fun getCitaById(id: Long) = citaRepository.getCitaBy(id)

    fun modifyCita(id: Long, nuevoDia: String?, nuevaHora: String?) {
        viewModelScope.launch {
            // Si el usuario no cambia un campo, mantiene el valor original
            val diaFinal = nuevoDia ?: return@launch
            val horaFinal = nuevaHora ?: return@launch

            citaRepository.modifyById(id, diaFinal, horaFinal)
        }
    }
}