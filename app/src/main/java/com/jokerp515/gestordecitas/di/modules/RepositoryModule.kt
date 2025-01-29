package com.jokerp515.gestordecitas.di.modules

import com.jokerp515.gestordecitas.local.dao.CitaDao
import com.jokerp515.gestordecitas.local.repositories.CitaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideCitaRepository(citaDao: CitaDao): CitaRepository {
        return CitaRepository(citaDao)
    }
}