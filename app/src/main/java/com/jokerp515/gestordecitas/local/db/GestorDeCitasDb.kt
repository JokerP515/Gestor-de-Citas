package com.jokerp515.gestordecitas.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jokerp515.gestordecitas.local.dao.CitaDao
import com.jokerp515.gestordecitas.local.dao.UserDao
import com.jokerp515.gestordecitas.local.entities.CitaEntity
import com.jokerp515.gestordecitas.local.entities.UserEntity

@Database(
    entities = [UserEntity::class, CitaEntity::class],
    version = 1
)
abstract class GestorDeCitasDb : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val citaDao: CitaDao
}