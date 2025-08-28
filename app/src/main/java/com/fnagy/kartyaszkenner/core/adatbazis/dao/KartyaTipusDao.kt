package com.fnagy.kartyaszkenner.core.adatbazis.dao

import androidx.room.*
import com.fnagy.kartyaszkenner.adatok.modellek.KartyaTipus
import kotlinx.coroutines.flow.Flow

/**
 * KártyaTípus DAO
 */
@Dao
interface KartyaTipusDao {
    
    @Query("SELECT * FROM kartya_tipusok ORDER BY nev ASC")
    fun osszesKartyaTipus(): Flow<List<KartyaTipus>>
    
    @Query("SELECT * FROM kartya_tipusok WHERE id = :id")
    suspend fun kartyaTipusById(id: Long): KartyaTipus?
    
    @Query("SELECT * FROM kartya_tipusok WHERE nev = :nev LIMIT 1")
    suspend fun kartyaTipusByNev(nev: String): KartyaTipus?
    
    @Query("SELECT * FROM kartya_tipusok WHERE kategoria = :kategoria ORDER BY nev ASC")
    fun kartyaTipusokKategoriaAlapjan(kategoria: String): Flow<List<KartyaTipus>>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun kartyaTipusBeszuras(kartyaTipus: KartyaTipus): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun kartyaTipusokBeszuras(kartyaTipusok: List<KartyaTipus>): List<Long>
    
    @Update
    suspend fun kartyaTipusFrissites(kartyaTipus: KartyaTipus)
    
    @Delete
    suspend fun kartyaTipusTorles(kartyaTipus: KartyaTipus)
    
    @Query("DELETE FROM kartya_tipusok")
    suspend fun osszesKartyaTipusTorles()
}