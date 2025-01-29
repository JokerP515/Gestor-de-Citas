package com.jokerp515.gestordecitas.di.modules

import android.app.Application
import androidx.room.Room
import com.jokerp515.gestordecitas.local.db.GestorDeCitasDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DBModule {
    @Provides
    @Singleton
    fun provideGestorDeCitasDb(app: Application): GestorDeCitasDb {
        return Room.databaseBuilder(
            app,
            GestorDeCitasDb::class.java,
            "gestocitasdb.db"
        ).build()
    }
}