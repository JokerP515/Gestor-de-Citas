package com.jokerp515.gestordecitas.viewmodel.usuarios

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jokerp515.gestordecitas.local.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private val _isUserRegistered = MutableStateFlow<Boolean?>(null)
    val isUserRegistered: StateFlow<Boolean?> get() = _isUserRegistered

    init {
        viewModelScope.launch {
            _isUserRegistered.value = userRepository.isUserRegistered()
        }
    }
}