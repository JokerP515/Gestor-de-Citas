package com.jokerp515.gestordecitas.data.user

data class User(
    val id: Long,
    val name: String,
    val phoneNumber: Long,
    val email: String? = null
)
