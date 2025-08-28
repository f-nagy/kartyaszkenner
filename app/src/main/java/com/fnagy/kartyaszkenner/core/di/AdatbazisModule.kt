package com.fnagy.kartyaszkenner.core.di

import android.content.Context
import androidx.room.Room
import com.fnagy.kartyaszkenner.core.adatbazis.KartyaAdatbazis
import com.fnagy.kartyaszkenner.core.adatbazis.dao.KartyaDao
import com.fnagy.kartyaszkenner.core.adatbazis.dao.KartyaTipusDao
import com.fnagy.kartyaszkenner.core.adatbazis.dao.IdeiglenesKartyaDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Adatbázis dependency injection module
 */
@Module
@InstallIn(SingletonComponent::class)
object AdatbazisModule {
    
    @Provides
    @Singleton
    fun provideKartyaAdatbazis(
        @ApplicationContext context: Context
    ): KartyaAdatbazis {
        return Room.databaseBuilder(
            context.applicationContext,
            KartyaAdatbazis::class.java,
            KartyaAdatbazis.ADATBAZIS_NEV
        )
        .fallbackToDestructiveMigration()
        .build()
    }
    
    @Provides
    fun provideKartyaDao(database: KartyaAdatbazis): KartyaDao {
        return database.kartyaDao()
    }
    
    @Provides
    fun provideKartyaTipusDao(database: KartyaAdatbazis): KartyaTipusDao {
        return database.kartyaTipusDao()
    }
    
    @Provides
    fun provideIdeiglenesKartyaDao(database: KartyaAdatbazis): IdeiglenesKartyaDao {
        return database.ideiglenesKartyaDao()
    }
}