package com.fnagy.kartyaszkenner.adatok.repository

import com.fnagy.kartyaszkenner.adatok.modellek.Kartya
import com.fnagy.kartyaszkenner.core.adatbazis.dao.KartyaDao
import com.fnagy.kartyaszkenner.adatok.api.YugiohApiService
import com.fnagy.kartyaszkenner.adatok.api.dto.YugiohKartyaDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Kártya repository - adatok kezelése
 */
@Singleton
class KartyaRepository @Inject constructor(
    private val kartyaDao: KartyaDao,
    private val apiService: YugiohApiService
) {
    
    /**
     * Összes kártya lekérdezése
     */
    fun osszesKartya(): Flow<List<Kartya>> = kartyaDao.osszesKartya()
    
    /**
     * Kártya keresése név alapján
     */
    suspend fun kartyaKeresese(nev: String): Kartya? {
        // Először helyi adatbázisban keresünk
        val helyi = kartyaDao.kartyaByNev(nev)
        if (helyi != null) {
            return helyi
        }
        
        // Ha nincs helyben, API-ból próbáljuk
        return try {
            val valasz = apiService.kartyaKeresese(nev)
            if (valasz.isSuccessful && valasz.body()?.data?.isNotEmpty() == true) {
                val apiKartya = valasz.body()!!.data!![0]
                val kartya = apiKartyaKonvertalas(apiKartya)
                kartyaDao.kartyaBeszuras(kartya)
                kartya
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
    
    /**
     * Kártya hozzáadása a gyűjteményhez
     */
    suspend fun kartyaHozzaadas(kartya: Kartya): Long {
        val meglevo = kartyaDao.kartyaByNev(kartya.nev)
        return if (meglevo != null) {
            // Ha már létezik, darabszámot növeljük
            kartyaDao.darabszamNoveles(meglevo.id, kartya.darabszam)
            meglevo.id
        } else {
            // Új kártya hozzáadása
            kartyaDao.kartyaBeszuras(kartya)
        }
    }
    
    /**
     * Kártya törlése
     */
    suspend fun kartyaTorles(kartya: Kartya) {
        kartyaDao.kartyaTorles(kartya)
    }
    
    /**
     * Darabszám módosítása
     */
    suspend fun darabszamModositas(kartyaId: Long, ujDarabszam: Int) {
        val kartya = kartyaDao.kartyaById(kartyaId)
        if (kartya != null) {
            val frissitett = kartya.copy(
                darabszam = ujDarabszam,
                utolsoModositas = System.currentTimeMillis()
            )
            kartyaDao.kartyaFrissites(frissitett)
        }
    }
    
    /**
     * Kártyák keresése szöveg alapján
     */
    fun kartyakKereses(keresoSzoveg: String): Flow<List<Kartya>> {
        return kartyaDao.kartyakKereses(keresoSzoveg)
    }
    
    /**
     * Kártyák típus szerint
     */
    fun kartyakTipusAlapjan(tipus: String): Flow<List<Kartya>> {
        return kartyaDao.kartyakTipusAlapjan(tipus)
    }
    
    /**
     * Gyűjtemény statisztikák
     */
    suspend fun gyujtemenyStatisztikak(): GyujtemenyStatisztikak {
        val kartyakSzama = kartyaDao.kartyakSzama()
        val osszesKartyaDarab = kartyaDao.osszesKartyaDarab()
        val tipusok = kartyaDao.osszesKartyaTipus()
        val ritkaságok = kartyaDao.osszesRitkaság()
        
        return GyujtemenyStatisztikak(
            kartyakSzama = kartyakSzama,
            osszesKartyaDarab = osszesKartyaDarab,
            tipusok = tipusok,
            ritkaságok = ritkaságok
        )
    }
    
    /**
     * API kártya konvertálása belső modellre
     */
    private fun apiKartyaKonvertalas(apiKartya: YugiohKartyaDto): Kartya {
        return Kartya(
            id = apiKartya.id,
            nev = apiKartya.name,
            tipus = apiKartya.type,
            leiras = apiKartya.desc,
            tamadas = apiKartya.atk,
            vedelem = apiKartya.def,
            szint = apiKartya.level,
            attributum = apiKartya.attribute,
            ritkaság = apiKartya.cardSets?.firstOrNull()?.setRarity,
            archiTipus = apiKartya.archetype,
            kepUrl = apiKartya.cardImages?.firstOrNull()?.imageUrl,
            kisKepUrl = apiKartya.cardImages?.firstOrNull()?.imageUrlSmall,
            eredetiNev = apiKartya.name
        )
    }
}

/**
 * Gyűjtemény statisztikák adatstruktúra
 */
data class GyujtemenyStatisztikak(
    val kartyakSzama: Int,
    val osszesKartyaDarab: Int,
    val tipusok: List<String>,
    val ritkaságok: List<String>
)