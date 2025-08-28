package com.fnagy.kartyaszkenner.core.beallitasok

/**
 * Beállítás kulcsok konstansai
 */
object BeallitasKulcsok {
    
    // Felismerési beállítások
    const val FELISMERES_SEBESSEG = "felismeres_sebesseg"
    const val FELISMERES_PONTOSSAG = "felismeres_pontossag"
    const val AUTODETEKTALAS_ENGEDELYEZVE = "autodetektalas_engedelyezve"
    
    // Hang és vibráció
    const val HANGJELZESEK_ENGEDELYEZVE = "hangjelzesek_engedelyezve"
    const val VIBRACIO_ENGEDELYEZVE = "vibracio_engedelyezve"
    const val HANGERŐSSÉG = "hangerősség"
    
    // Kép beállítások
    const val KEP_MINOSEG = "kep_minoseg"
    const val KEP_TOMORITESI_ARANY = "kep_tomoritesi_arany"
    
    // Adatkezelés
    const val DUPLIKATUM_KEZELES = "duplikatum_kezeles"
    const val OFFLINE_MOD = "offline_mod"
    const val AUTO_MENTES = "auto_mentes"
    const val MENTESI_GYAKORISAG = "mentesi_gyakorisag"
    
    // Export
    const val EXPORT_FORMATUM = "export_formatum"
    const val EXPORT_KEPEKKEL = "export_kepekkel"
    
    // Nyelv és megjelenés
    const val NYELV_KOD = "nyelv_kod"
    const val SOTET_TEMA = "sotet_tema"
    
    // Fejlett beállítások
    const val FEJLESZTOI_MOD = "fejlesztoi_mod"
    const val LOGOLAS = "logolas"
    
    // Alapértelmezett értékek
    const val ALAPERTELMEZETT_FELISMERES_SEBESSEG = 1 // Közepes
    const val ALAPERTELMEZETT_FELISMERES_PONTOSSAG = 1 // Közepes
    const val ALAPERTELMEZETT_HANGERERŐSSÉG = 0.7f
    const val ALAPERTELMEZETT_KEP_MINOSEG = 1 // Közepes
    const val ALAPERTELMEZETT_KEP_TOMORITESI_ARANY = 0.8f
    const val ALAPERTELMEZETT_DUPLIKATUM_KEZELES = 1 // Egyesítés
    const val ALAPERTELMEZETT_MENTESI_GYAKORISAG = 60 // másodperc
    const val ALAPERTELMEZETT_EXPORT_FORMATUM = 0 // CSV
    const val ALAPERTELMEZETT_NYELV_KOD = "hu"
}