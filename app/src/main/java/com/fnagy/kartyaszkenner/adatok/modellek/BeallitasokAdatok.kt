package com.fnagy.kartyaszkenner.adatok.modellek

/**
 * Alkalmazás beállítások adatstruktúrája
 */
data class BeallitasokAdatok(
    // Felismerési beállítások
    val felismeresiSebesseg: FelismeresiSebesseg = FelismeresiSebesseg.KOZEPES,
    val felismeresiPontossag: FelismeresiPontossag = FelismeresiPontossag.KOZEPES,
    
    // Hang és vibráció
    val hangjelzesekEngedelyezve: Boolean = true,
    val vibracioEngedelyezve: Boolean = true,
    val hangerősség: Float = 0.7f,
    
    // Kép beállítások  
    val kepMinoseg: KepMinoseg = KepMinoseg.KOZEPES,
    val kepTomoritesiArany: Float = 0.8f,
    
    // Adatkezelés
    val duplikatumKezeles: DuplikatumKezeles = DuplikatumKezeles.EGYESITES,
    val offlineMod: Boolean = false,
    
    // Exportálás
    val exportFormatum: ExportFormatum = ExportFormatum.CSV,
    val exportKepekkel: Boolean = false,
    
    // Nyelv és megjelenés
    val nyelvKod: String = "hu",
    val sotetTema: Boolean = false,
    
    // Adatbázis beállítások
    val autoMentes: Boolean = true,
    val mentesiGyakorisag: Int = 60, // másodpercek
    
    // Fejlett beállítások
    val fejlesztoimod: Boolean = false,
    val logolas: Boolean = false
)

/**
 * Felismerési sebesség enum
 */
enum class FelismeresiSebesseg {
    LASSÚ,     // Nagy pontosság, lassabb feldolgozás
    KOZEPES,   // Egyensúly
    GYORS      // Gyors felismerés, kisebb pontosság
}

/**
 * Felismerési pontosság enum
 */
enum class FelismeresiPontossag {
    ALACSONY,  // Gyors, de kevésbé pontos
    KOZEPES,   // Egyensúly
    MAGAS      // Nagyon pontos, de lassabb
}

/**
 * Kép minőség enum
 */
enum class KepMinoseg {
    ALACSONY,  // 480p
    KOZEPES,   // 720p
    MAGAS      // 1080p+
}

/**
 * Duplikátum kezelés enum
 */
enum class DuplikatumKezeles {
    ENGEDELYEZESE,  // Duplikátumok megengedése
    EGYESITES,      // Duplikátumok egyesítése (darabszám növelése)
    TILTASA         // Duplikátumok tiltása
}

/**
 * Export formátum enum
 */
enum class ExportFormatum {
    CSV,
    JSON,
    XML
}