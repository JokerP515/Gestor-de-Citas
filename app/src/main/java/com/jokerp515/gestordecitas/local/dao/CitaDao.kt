package com.jokerp515.gestordecitas.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.jokerp515.gestordecitas.local.entities.CitaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CitaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarCita(cita: CitaEntity)

    @Query("SELECT * FROM citas WHERE dia = :dia AND especialidad = :especialidad") // Sobra
    suspend fun obtenerCitasPorDiaYEspecialidad(dia: String, especialidad: String): List<CitaEntity>

    @Query("SELECT * FROM citas")
    fun obtenerCitas(): Flow<List<CitaEntity>>

    @Transaction
    @Query("DELETE FROM citas WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("SELECT * FROM citas WHERE id = :id") // Posiblemente no necesario
    suspend fun getCitaById(id: Long): CitaEntity?

    @Transaction
    @Query("UPDATE citas SET dia = :dia, hora = :hora WHERE id = :id")
    suspend fun modifyById(id: Long, dia: String, hora: String)


}