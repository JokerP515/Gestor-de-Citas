package com.jokerp515.gestordecitas.local.repositories

import com.jokerp515.gestordecitas.local.dao.CitaDao
import com.jokerp515.gestordecitas.local.entities.CitaEntity
import javax.inject.Inject

class CitaRepository
    @Inject constructor(
        private val citaDao: CitaDao
    ) {
    suspend fun insertarCita(cita: CitaEntity) = citaDao.insertarCita(cita)

    suspend fun obtenerCitasPorDiaYEspecialidad(dia: String, especialidad: String) =
        citaDao.obtenerCitasPorDiaYEspecialidad(dia, especialidad)

    fun obtenerCitas() = citaDao.obtenerCitas()

    suspend fun deleteById(id: Long) = citaDao.deleteById(id)

    suspend fun getCitaBy(id: Long) = citaDao.getCitaById(id)

    suspend fun modifyById(id: Long, dia: String, hora: String) = citaDao.modifyById(id, dia, hora)

    //suspend fun editarCita(cita: CitaEntity) = citaDao.editarCita(cita)
}
