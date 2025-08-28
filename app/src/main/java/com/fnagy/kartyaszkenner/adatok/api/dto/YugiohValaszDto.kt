package com.fnagy.kartyaszkenner.adatok.api.dto

import com.google.gson.annotations.SerializedName

/**
 * Yu-Gi-Oh API válasz DTO
 */
data class YugiohValaszDto(
    @SerializedName("data")
    val data: List<YugiohKartyaDto>? = null,
    
    @SerializedName("meta")
    val meta: YugiohMetaDto? = null
)

/**
 * Meta információk a válaszban
 */
data class YugiohMetaDto(
    @SerializedName("current_rows")
    val currentRows: Int? = null,
    
    @SerializedName("total_rows")
    val totalRows: Int? = null,
    
    @SerializedName("rows_remaining")
    val rowsRemaining: Int? = null,
    
    @SerializedName("total_pages")
    val totalPages: Int? = null,
    
    @SerializedName("pages_remaining")
    val pagesRemaining: Int? = null
)