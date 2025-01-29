package com.jokerp515.gestordecitas.local.repositories

import com.jokerp515.gestordecitas.local.dao.UserDao
import com.jokerp515.gestordecitas.local.entities.UserEntity
import javax.inject.Inject

class UserRepository
    @Inject constructor(
        private val userDao: UserDao
    ) {
    suspend fun insertUser(user: UserEntity) = userDao.insertUser(user)
    suspend fun getUser() = userDao.getUser()
    suspend fun isUserRegistered(): Boolean {
        return userDao.getUserCount() > 0
    }
    suspend fun modifyById(id: Long, newName: String, newPhone: Long, newEmail: String) {
        val user = UserEntity(id, newName, newPhone, newEmail)
        userDao.insertUser(user)
    }
}
