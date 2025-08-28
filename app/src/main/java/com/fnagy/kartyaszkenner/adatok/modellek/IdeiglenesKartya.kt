package com.fnagy.kartyaszkenner.adatok.modellek

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Ideiglenes szkennelt kártya entitás
 * A felismerési folyamat során használt átmeneti tároláshoz
 */
@Entity(tableName = "ideiglenes_kartyak")
data class IdeiglenesKartya(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val felismertSzoveg: String,
    val felismeresiBizalom: Float, // 0.0 - 1.0
    val kartyaId: Long? = null, // Ha sikerült azonosítani
    val kepHash: String? = null, // Kép hash a duplikáció elkerülésére
    val kepUtvonal: String? = null,
    val felismeresIdeje: Long = System.currentTimeMillis(),
    val allapot: FelismeresiAllapot = FelismeresiAllapot.FELDOLGOZAS_ALATT,
    val hibaUzenet: String? = null
)

/**
 * Felismerési állapot enum
 */
enum class FelismeresiAllapot {
    FELDOLGOZAS_ALATT,
    SIKERES,
    BIZONYTALAN,
    SIKERTELEN,
    HIBA
}