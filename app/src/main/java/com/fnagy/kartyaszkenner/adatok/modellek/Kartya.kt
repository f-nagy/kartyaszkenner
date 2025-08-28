package com.fnagy.kartyaszkenner.adatok.modellek

import androidx.room.Entity
import androidx.room.PrimaryKey
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Kártya entitás az adatbázishoz
 */
@Entity(tableName = "kartyak")
@Parcelize
data class Kartya(
    @PrimaryKey val id: Long = 0,
    val nev: String,
    val tipus: String,
    val leiras: String? = null,
    val tamadas: Int? = null,
    val vedelem: Int? = null,
    val szint: Int? = null,
    val attributum: String? = null,
    val ritkaság: String? = null,
    val szet: String? = null,
    val archiTipus: String? = null,
    val kepUrl: String? = null,
    val kisKepUrl: String? = null,
    val darabszam: Int = 1,
    val hozzaadasDatuma: Long = System.currentTimeMillis(),
    val utolsoModositas: Long = System.currentTimeMillis(),
    val nyelvKod: String = "hu",
    val eredetiNev: String? = null
) : Parcelable {
    
    /**
     * Ellenőrzi, hogy ez egy szörny kártya-e
     */
    fun isSzornyKartya(): Boolean {
        return tipus.contains("Monster", ignoreCase = true) || 
               tipus.contains("Szörny", ignoreCase = true)
    }
    
    /**
     * Ellenőrzi, hogy ez egy varázslat kártya-e
     */
    fun isVarazslatKartya(): Boolean {
        return tipus.contains("Spell", ignoreCase = true) || 
               tipus.contains("Varázslat", ignoreCase = true)
    }
    
    /**
     * Ellenőrzi, hogy ez egy csapda kártya-e
     */
    fun isCsapdaKartya(): Boolean {
        return tipus.contains("Trap", ignoreCase = true) || 
               tipus.contains("Csapda", ignoreCase = true)
    }
    
    /**
     * Ritkaság színének meghatározása
     */
    fun getRitkaságSzin(): String {
        return when (ritkaság?.lowercase()) {
            "common" -> "#6B7280"
            "rare" -> "#3B82F6"
            "super rare" -> "#8B5CF6"
            "ultra rare" -> "#F59E0B"
            "secret rare" -> "#EF4444"
            else -> "#6B7280"
        }
    }
}