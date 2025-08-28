package com.fnagy.kartyaszkenner.core.adatbazis.dao

import androidx.room.*
import com.fnagy.kartyaszkenner.adatok.modellek.Kartya
import kotlinx.coroutines.flow.Flow

/**
 * Kártya DAO - adatbázis műveletekhez
 */
@Dao
interface KartyaDao {
    
    @Query("SELECT * FROM kartyak ORDER BY hozzaadasDatuma DESC")
    fun osszesKartya(): Flow<List<Kartya>>
    
    @Query("SELECT * FROM kartyak WHERE id = :id")
    suspend fun kartyaById(id: Long): Kartya?
    
    @Query("SELECT * FROM kartyak WHERE nev LIKE :nev LIMIT 1")
    suspend fun kartyaByNev(nev: String): Kartya?
    
    @Query("SELECT * FROM kartyak WHERE nev LIKE '%' || :keresoSzoveg || '%' OR leiras LIKE '%' || :keresoSzoveg || '%'")
    fun kartyakKereses(keresoSzoveg: String): Flow<List<Kartya>>
    
    @Query("SELECT * FROM kartyak WHERE tipus = :tipus ORDER BY nev ASC")
    fun kartyakTipusAlapjan(tipus: String): Flow<List<Kartya>>
    
    @Query("SELECT * FROM kartyak WHERE ritkaság = :ritkaság ORDER BY nev ASC")
    fun kartyakRitkaságAlapjan(ritkaság: String): Flow<List<Kartya>>
    
    @Query("SELECT COUNT(*) FROM kartyak")
    suspend fun kartyakSzama(): Int
    
    @Query("SELECT SUM(darabszam) FROM kartyak")
    suspend fun osszesKartyaDarab(): Int
    
    @Query("SELECT DISTINCT tipus FROM kartyak ORDER BY tipus ASC")
    suspend fun osszesKartyaTipus(): List<String>
    
    @Query("SELECT DISTINCT ritkaság FROM kartyak WHERE ritkaság IS NOT NULL ORDER BY ritkaság ASC")
    suspend fun osszesRitkaság(): List<String>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun kartyaBeszuras(kartya: Kartya): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun kartyakBeszuras(kartyak: List<Kartya>): List<Long>
    
    @Update
    suspend fun kartyaFrissites(kartya: Kartya)
    
    @Delete
    suspend fun kartyaTorles(kartya: Kartya)
    
    @Query("DELETE FROM kartyak WHERE id = :id")
    suspend fun kartyaTorlesById(id: Long)
    
    @Query("DELETE FROM kartyak")
    suspend fun osszesKartyaTorles()
    
    @Query("UPDATE kartyak SET darabszam = darabszam + :mennyiseg WHERE id = :id")
    suspend fun darabszamNoveles(id: Long, mennyiseg: Int = 1)
    
    @Query("UPDATE kartyak SET darabszam = darabszam - :mennyiseg WHERE id = :id AND darabszam > :mennyiseg")
    suspend fun darabszamCsokentes(id: Long, mennyiseg: Int = 1)
}