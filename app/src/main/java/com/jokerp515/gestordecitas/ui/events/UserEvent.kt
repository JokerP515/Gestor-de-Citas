package com.jokerp515.gestordecitas.ui.events

sealed class UserEvent {
    data class OnNameChange(val name: String) : UserEvent()
    data class OnIdChange(val id: Long) : UserEvent()
    data class OnPhoneChange(val phone: Long) : UserEvent()
    data class OnEmailChange(val email: String?) : UserEvent()
    data object onSave : UserEvent()
}