package com.fnagy.kartyaszkenner.core.adatbazis.dao

import androidx.room.*
import com.fnagy.kartyaszkenner.adatok.modellek.IdeiglenesKartya
import com.fnagy.kartyaszkenner.adatok.modellek.FelismeresiAllapot
import kotlinx.coroutines.flow.Flow

/**
 * Ideiglenes kártya DAO
 */
@Dao
interface IdeiglenesKartyaDao {
    
    @Query("SELECT * FROM ideiglenes_kartyak ORDER BY felismeresIdeje DESC")
    fun osszesIdeiglenesKartya(): Flow<List<IdeiglenesKartya>>
    
    @Query("SELECT * FROM ideiglenes_kartyak WHERE id = :id")
    suspend fun ideiglenesKartyaById(id: Long): IdeiglenesKartya?
    
    @Query("SELECT * FROM ideiglenes_kartyak WHERE allapot = :allapot ORDER BY felismeresIdeje DESC")
    fun ideiglenesKartyakAllapotAlapjan(allapot: FelismeresiAllapot): Flow<List<IdeiglenesKartya>>
    
    @Query("SELECT * FROM ideiglenes_kartyak WHERE kepHash = :hash LIMIT 1")
    suspend fun ideiglenesKartyaByHash(hash: String): IdeiglenesKartya?
    
    @Query("SELECT COUNT(*) FROM ideiglenes_kartyak")
    suspend fun ideiglenesKartyakSzama(): Int
    
    @Query("SELECT COUNT(*) FROM ideiglenes_kartyak WHERE allapot = :allapot")
    suspend fun ideiglenesKartyakSzamaAllapotAlapjan(allapot: FelismeresiAllapot): Int
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun ideiglenesKartyaBeszuras(ideiglenesKartya: IdeiglenesKartya): Long
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun ideiglenesKartyakBeszuras(ideiglenesKartyak: List<IdeiglenesKartya>): List<Long>
    
    @Update
    suspend fun ideiglenesKartyaFrissites(ideiglenesKartya: IdeiglenesKartya)
    
    @Delete
    suspend fun ideiglenesKartyaTorles(ideiglenesKartya: IdeiglenesKartya)
    
    @Query("DELETE FROM ideiglenes_kartyak WHERE id = :id")
    suspend fun ideiglenesKartyaTorlesById(id: Long)
    
    @Query("DELETE FROM ideiglenes_kartyak")
    suspend fun osszesIdeiglenesKartyaTorles()
    
    @Query("DELETE FROM ideiglenes_kartyak WHERE allapot = :allapot")
    suspend fun ideiglenesKartyakTorleseAllapotAlapjan(allapot: FelismeresiAllapot)
    
    @Query("DELETE FROM ideiglenes_kartyak WHERE felismeresIdeje < :datum")
    suspend fun regiIdeiglenesKartyakTorles(datum: Long)
}