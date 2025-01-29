package com.jokerp515.gestordecitas.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey val id: Long,
    val name: String,
    val phone: Long,
    val email: String? = null
)