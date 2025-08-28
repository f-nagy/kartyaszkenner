package com.fnagy.kartyaszkenner.adatok.modellek

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Kártya típus entitás
 */
@Entity(tableName = "kartya_tipusok")
data class KartyaTipus(
    @PrimaryKey val id: Long = 0,
    val nev: String,
    val kategoria: String, // Monster, Spell, Trap
    val alkategoria: String? = null, // Normal, Effect, Fusion stb.
    val leiras: String? = null,
    val ikonResId: Int? = null
) {
    companion object {
        // Yu-Gi-Oh! alaptípusok
        val SZORNY_TIPUSOK = listOf(
            "Normal Monster",
            "Effect Monster", 
            "Fusion Monster",
            "Synchro Monster",
            "Xyz Monster",
            "Pendulum Monster",
            "Link Monster",
            "Ritual Monster"
        )
        
        val VARAZSLAT_TIPUSOK = listOf(
            "Normal Spell",
            "Quick-Play Spell",
            "Continuous Spell",
            "Equip Spell",
            "Field Spell",
            "Ritual Spell"
        )
        
        val CSAPDA_TIPUSOK = listOf(
            "Normal Trap",
            "Continuous Trap",
            "Counter Trap"
        )
    }
}