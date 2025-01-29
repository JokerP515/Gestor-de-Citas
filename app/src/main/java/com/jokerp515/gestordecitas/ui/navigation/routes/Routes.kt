package com.jokerp515.gestordecitas.ui.navigation.routes

import kotlinx.serialization.Serializable

@Serializable
sealed class Routes (val id: String? = null) {
    // Posibles rutas al iniciar la app
    // Toma la de registro si no hay usuario registrado de lo contrario se va al menu principal
    @Serializable
    data object Registro : Routes("registro")
    @Serializable
    data object MenuPrincipal : Routes("menu_principal")

    // Rutas a partir de Menu Principal
    @Serializable
    data object RegistroCitas : Routes("registro_de_citas")
    @Serializable
    data object ListaCitas : Routes("lista_de_citas")

    // Rutas para modificación
    @Serializable
    data object ModificarUsuario : Routes("modificar_usuario")

    @Serializable
    data class ModificarCitaId(val citaId: Long) : Routes("modificar_cita")

}