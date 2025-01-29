package com.jokerp515.gestordecitas.data.citas

enum class Especialidad(val displayName: String) {
    CARDIOLOGIA("Cardiología"),
    DERMATOLOGIA("Dermatología"),
    PEDIATRIA("Pediatría"),
    GINECOLOGIA("Ginecología"),
    ORTOPEDIA("Ortopedia");

    override fun toString(): String = displayName
}