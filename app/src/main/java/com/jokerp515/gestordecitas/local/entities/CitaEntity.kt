package com.jokerp515.gestordecitas.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "citas")
data class CitaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val especialidad: String,
    val dia: String,
    val hora: String
)