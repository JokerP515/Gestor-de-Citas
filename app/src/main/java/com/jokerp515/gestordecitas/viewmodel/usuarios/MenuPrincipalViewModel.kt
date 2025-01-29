package com.jokerp515.gestordecitas.viewmodel.usuarios

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokerp515.gestordecitas.local.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuPrincipalViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {
    var welcomeMessage by mutableStateOf("")

    init {
        viewModelScope.launch {
            val user = userRepository.getUser()
            welcomeMessage = user?.name?.let { "¡Bienvenido, $it!" } ?: "¡Bienvenido, invitado"
        }
    }
}