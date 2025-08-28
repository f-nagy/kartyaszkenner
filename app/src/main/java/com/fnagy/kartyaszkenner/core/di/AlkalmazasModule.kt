package com.fnagy.kartyaszkenner.core.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Alkalmazás szintű dependency injection module
 */
@Module
@InstallIn(SingletonComponent::class)
object AlkalmazasModule {
    
    @Provides
    @Singleton
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }
}