package com.fnagy.kartyaszkenner.adatok.modellek

/**
 * Felismert kártya adatstruktúra a UI számára
 */
data class FelismertKartya(
    val kartya: Kartya? = null,
    val felismeresiBizalom: Float = 0.0f,
    val felismertSzoveg: String = "",
    val kepHash: String? = null,
    val pozicio: KartyaPozicio? = null,
    val felismeresIdeje: Long = System.currentTimeMillis()
)

/**
 * Kártya pozíciója a kamerában
 */
data class KartyaPozicio(
    val x: Float,
    val y: Float, 
    val szelesseg: Float,
    val magassag: Float,
    val forgatasi_szog: Float = 0.0f
) {
    /**
     * Ellenőrzi, hogy a pozíció érvényes-e
     */
    fun isErvenyes(): Boolean {
        return x >= 0 && y >= 0 && szelesseg > 0 && magassag > 0
    }
    
    /**
     * Kártya középpontjának kiszámítása
     */
    fun getKozeppont(): Pair<Float, Float> {
        return Pair(x + szelesseg / 2, y + magassag / 2)
    }
}