package com.fnagy.kartyaszkenner.adatok.api.dto

import com.google.gson.annotations.SerializedName

/**
 * Yu-Gi-Oh kártya DTO az API-hoz
 */
data class YugiohKartyaDto(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("type")
    val type: String,
    
    @SerializedName("frameType")
    val frameType: String? = null,
    
    @SerializedName("desc")
    val desc: String? = null,
    
    @SerializedName("atk")
    val atk: Int? = null,
    
    @SerializedName("def")
    val def: Int? = null,
    
    @SerializedName("level")
    val level: Int? = null,
    
    @SerializedName("race")
    val race: String? = null,
    
    @SerializedName("attribute")
    val attribute: String? = null,
    
    @SerializedName("archetype")
    val archetype: String? = null,
    
    @SerializedName("ygoprodeck_url")
    val ygoprodeckUrl: String? = null,
    
    @SerializedName("card_sets")
    val cardSets: List<YugiohKartyaSzetDto>? = null,
    
    @SerializedName("card_images")
    val cardImages: List<YugiohKartyaKepDto>? = null,
    
    @SerializedName("card_prices")
    val cardPrices: List<YugiohKartyaArDto>? = null
)

/**
 * Kártya szett információ
 */
data class YugiohKartyaSzetDto(
    @SerializedName("set_name")
    val setName: String,
    
    @SerializedName("set_code")
    val setCode: String,
    
    @SerializedName("set_rarity")
    val setRarity: String? = null,
    
    @SerializedName("set_rarity_code")
    val setRarityCode: String? = null,
    
    @SerializedName("set_price")
    val setPrice: String? = null
)

/**
 * Kártya kép információ
 */
data class YugiohKartyaKepDto(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("image_url")
    val imageUrl: String,
    
    @SerializedName("image_url_small")
    val imageUrlSmall: String,
    
    @SerializedName("image_url_cropped")
    val imageUrlCropped: String? = null
)

/**
 * Kártya ár információ
 */
data class YugiohKartyaArDto(
    @SerializedName("cardmarket_price")
    val cardmarketPrice: String? = null,
    
    @SerializedName("tcgplayer_price")
    val tcgplayerPrice: String? = null,
    
    @SerializedName("ebay_price")
    val ebayPrice: String? = null,
    
    @SerializedName("amazon_price")
    val amazonPrice: String? = null,
    
    @SerializedName("coolstuffinc_price")
    val coolstuffincPrice: String? = null
)