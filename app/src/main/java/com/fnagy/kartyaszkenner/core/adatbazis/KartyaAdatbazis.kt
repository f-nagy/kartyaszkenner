package com.fnagy.kartyaszkenner.core.adatbazis

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import android.content.Context
import com.fnagy.kartyaszkenner.adatok.modellek.Kartya
import com.fnagy.kartyaszkenner.adatok.modellek.KartyaTipus
import com.fnagy.kartyaszkenner.adatok.modellek.IdeiglenesKartya
import com.fnagy.kartyaszkenner.core.adatbazis.dao.KartyaDao
import com.fnagy.kartyaszkenner.core.adatbazis.dao.KartyaTipusDao
import com.fnagy.kartyaszkenner.core.adatbazis.dao.IdeiglenesKartyaDao
import com.fnagy.kartyaszkenner.core.adatbazis.konverterek.AdatbazisKonverterek

/**
 * Room adatbázis osztály
 */
@Database(
    entities = [
        Kartya::class,
        KartyaTipus::class,
        IdeiglenesKartya::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(AdatbazisKonverterek::class)
abstract class KartyaAdatbazis : RoomDatabase() {
    
    abstract fun kartyaDao(): KartyaDao
    abstract fun kartyaTipusDao(): KartyaTipusDao
    abstract fun ideiglenesKartyaDao(): IdeiglenesKartyaDao
    
    companion object {
        const val ADATBAZIS_NEV = "kartyaszkenner.db"
        
        @Volatile
        private var INSTANCE: KartyaAdatbazis? = null
        
        fun getDatabase(context: Context): KartyaAdatbazis {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    KartyaAdatbazis::class.java,
                    ADATBAZIS_NEV
                )
                .fallbackToDestructiveMigration() // Fejlesztési fázisban
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}