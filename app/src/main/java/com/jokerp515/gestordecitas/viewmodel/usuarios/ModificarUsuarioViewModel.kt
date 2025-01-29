package com.jokerp515.gestordecitas.viewmodel.usuarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokerp515.gestordecitas.local.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ModificarUsuarioViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    suspend fun getUser() = userRepository.getUser()

    fun modifyUser(id: Long, newName: String?, newPhone: Long?, newEmail: String?) {
        viewModelScope.launch {
            // Si el usuario no cambia un campo, mantiene el valor original
            val nameFinal = newName ?: return@launch
            val phoneFinal = newPhone ?: return@launch
            val emailFinal = newEmail ?: return@launch

            userRepository.modifyById(id, nameFinal, phoneFinal, emailFinal)
        }
    }

}