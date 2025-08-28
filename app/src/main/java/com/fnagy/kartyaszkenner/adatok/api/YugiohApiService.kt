package com.fnagy.kartyaszkenner.adatok.api

import com.fnagy.kartyaszkenner.adatok.api.dto.YugiohValaszDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Yu-Gi-Oh API service interface
 */
interface YugiohApiService {
    
    @GET("cardinfo.php")
    suspend fun kartyakLekerese(
        @Query("fname") nev: String? = null,
        @Query("id") id: Long? = null,
        @Query("type") tipus: String? = null,
        @Query("atk") tamadas: Int? = null,
        @Query("def") vedelem: Int? = null,
        @Query("level") szint: Int? = null,
        @Query("race") faj: String? = null,
        @Query("attribute") attributum: String? = null,
        @Query("archetype") archiTipus: String? = null,
        @Query("startdate") kezdoDatum: String? = null,
        @Query("enddate") vegDatum: String? = null,
        @Query("dateregion") datumRegio: String? = null,
        @Query("format") format: String? = null,
        @Query("misc") egyeb: String? = null,
        @Query("sort") rendezes: String? = null,
        @Query("num") szam: Int? = null,
        @Query("offset") eltolas: Int? = null
    ): Response<YugiohValaszDto>
    
    @GET("cardinfo.php")
    suspend fun kartyaKeresese(
        @Query("fname") nev: String
    ): Response<YugiohValaszDto>
    
    @GET("cardinfo.php")
    suspend fun kartyaById(
        @Query("id") id: Long
    ): Response<YugiohValaszDto>
    
    @GET("cardinfo.php")
    suspend fun kartyakTipusAlapjan(
        @Query("type") tipus: String,
        @Query("num") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<YugiohValaszDto>
    
    @GET("cardinfo.php")
    suspend fun kartyakArchiTipusAlapjan(
        @Query("archetype") archiTipus: String,
        @Query("num") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): Response<YugiohValaszDto>
    
    @GET("cardinfo.php")
    suspend fun randomKartyak(
        @Query("num") szam: Int = 10,
        @Query("sort") rendezes: String = "random"
    ): Response<YugiohValaszDto>
    
    companion object {
        const val BASE_URL = "https://db.ygoprodeck.com/api/v7/"
        
        // Gyakori keresési paraméterek
        const val SORT_NAME = "name"
        const val SORT_ATK = "atk"
        const val SORT_DEF = "def"
        const val SORT_LEVEL = "level"
        const val SORT_RANDOM = "random"
        
        // Formatumok
        const val FORMAT_TCG = "tcg"
        const val FORMAT_OCG = "ocg"
        const val FORMAT_RUSH = "rush duel"
        const val FORMAT_SPEED = "speed duel"
        
        // Datum régiók
        const val DATE_REGION_TCG = "tcg_date"
        const val DATE_REGION_OCG = "ocg_date"
    }
}