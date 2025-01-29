package com.jokerp515.gestordecitas.viewmodel.usuarios

import com.jokerp515.gestordecitas.ui.events.UserEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import com.jokerp515.gestordecitas.local.repositories.UserRepository
import com.jokerp515.gestordecitas.local.entities.UserEntity
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel
    @Inject constructor(
        private val userRepository: UserRepository
    ) : ViewModel() {

    var name by mutableStateOf("")
    var id by mutableLongStateOf(0L)
    var phone by mutableLongStateOf(0L)
    var email by mutableStateOf("")

    fun saveUser() {
        viewModelScope.launch {
            val user = UserEntity(id = id, name = name, phone = phone, email = email.takeIf { it.isNotBlank() })
            userRepository.insertUser(user)
        }
    }

    fun onEvent(event: UserEvent){
        when(event) {
            is UserEvent.OnNameChange -> name = event.name
            is UserEvent.OnIdChange -> id = event.id
            is UserEvent.OnPhoneChange -> phone = event.phone
            is UserEvent.OnEmailChange -> email = event.email.toString()
            is UserEvent.onSave -> {
                saveUser()
                reset()
            }
        }
    }

    private fun reset() {
        name = ""
        id = 0L
        phone = 0L
        email = ""
    }
}


