package com.jokerp515.gestordecitas.di.modules

import com.jokerp515.gestordecitas.local.dao.CitaDao
import com.jokerp515.gestordecitas.local.dao.UserDao
import com.jokerp515.gestordecitas.local.db.GestorDeCitasDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DaoModule {
    @Provides
    @Singleton
    fun provideGestorDeCitasDb(db: GestorDeCitasDb): UserDao {
        return db.userDao
    }
    @Provides
    @Singleton
    fun provideCitaDao(db: GestorDeCitasDb): CitaDao {
        return db.citaDao
    }
}