package com.fnagy.kartyaszkenner.core.beallitasok

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.fnagy.kartyaszkenner.adatok.modellek.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "beallitasok")

/**
 * Beállítások kezelő osztály
 */
@Singleton
class BeallitasokKezelo @Inject constructor(
    private val context: Context
) {
    
    // Preferencia kulcsok
    private val felismeresiSebessegKey = intPreferencesKey(BeallitasKulcsok.FELISMERES_SEBESSEG)
    private val felismeresiPontossagKey = intPreferencesKey(BeallitasKulcsok.FELISMERES_PONTOSSAG)
    private val hangjelzesekEngedelyezveKey = booleanPreferencesKey(BeallitasKulcsok.HANGJELZESEK_ENGEDELYEZVE)
    private val vibracioEngedelyezveKey = booleanPreferencesKey(BeallitasKulcsok.VIBRACIO_ENGEDELYEZVE)
    private val hangerősségKey = floatPreferencesKey(BeallitasKulcsok.HANGERŐSSÉG)
    private val kepMinosegKey = intPreferencesKey(BeallitasKulcsok.KEP_MINOSEG)
    private val kepTomoritesiAranyKey = floatPreferencesKey(BeallitasKulcsok.KEP_TOMORITESI_ARANY)
    private val duplikatumKezelesKey = intPreferencesKey(BeallitasKulcsok.DUPLIKATUM_KEZELES)
    private val offlineModKey = booleanPreferencesKey(BeallitasKulcsok.OFFLINE_MOD)
    private val exportFormatumKey = intPreferencesKey(BeallitasKulcsok.EXPORT_FORMATUM)
    private val exportKepekkelKey = booleanPreferencesKey(BeallitasKulcsok.EXPORT_KEPEKKEL)
    private val nyelvKodKey = stringPreferencesKey(BeallitasKulcsok.NYELV_KOD)
    private val sotetTemaKey = booleanPreferencesKey(BeallitasKulcsok.SOTET_TEMA)
    private val fejlesztoiModKey = booleanPreferencesKey(BeallitasKulcsok.FEJLESZTOI_MOD)
    private val logolasKey = booleanPreferencesKey(BeallitasKulcsok.LOGOLAS)
    
    /**
     * Beállítások Flow lekérdezése
     */
    val beallitasokFlow: Flow<BeallitasokAdatok> = context.dataStore.data.map { preferences ->
        BeallitasokAdatok(
            felismeresiSebesseg = FelismeresiSebesseg.values()[
                preferences[felismeresiSebessegKey] ?: BeallitasKulcsok.ALAPERTELMEZETT_FELISMERES_SEBESSEG
            ],
            felismeresiPontossag = FelismeresiPontossag.values()[
                preferences[felismeresiPontossagKey] ?: BeallitasKulcsok.ALAPERTELMEZETT_FELISMERES_PONTOSSAG
            ],
            hangjelzesekEngedelyezve = preferences[hangjelzesekEngedelyezveKey] ?: true,
            vibracioEngedelyezve = preferences[vibracioEngedelyezveKey] ?: true,
            hangerősség = preferences[hangerősségKey] ?: BeallitasKulcsok.ALAPERTELMEZETT_HANGERERŐSSÉG,
            kepMinoseg = KepMinoseg.values()[
                preferences[kepMinosegKey] ?: BeallitasKulcsok.ALAPERTELMEZETT_KEP_MINOSEG
            ],
            kepTomoritesiArany = preferences[kepTomoritesiAranyKey] ?: BeallitasKulcsok.ALAPERTELMEZETT_KEP_TOMORITESI_ARANY,
            duplikatumKezeles = DuplikatumKezeles.values()[
                preferences[duplikatumKezelesKey] ?: BeallitasKulcsok.ALAPERTELMEZETT_DUPLIKATUM_KEZELES
            ],
            offlineMod = preferences[offlineModKey] ?: false,
            exportFormatum = ExportFormatum.values()[
                preferences[exportFormatumKey] ?: BeallitasKulcsok.ALAPERTELMEZETT_EXPORT_FORMATUM
            ],
            exportKepekkel = preferences[exportKepekkelKey] ?: false,
            nyelvKod = preferences[nyelvKodKey] ?: BeallitasKulcsok.ALAPERTELMEZETT_NYELV_KOD,
            sotetTema = preferences[sotetTemaKey] ?: false,
            fejlesztoimod = preferences[fejlesztoiModKey] ?: false,
            logolas = preferences[logolasKey] ?: false
        )
    }
    
    /**
     * Felismerési sebesség frissítése
     */
    suspend fun felismeresiSebessegFrissites(sebesség: FelismeresiSebesseg) {
        context.dataStore.edit { preferences ->
            preferences[felismeresiSebessegKey] = sebesség.ordinal
        }
    }
    
    /**
     * Hangjelzések kapcsolása
     */
    suspend fun hangjelzesekKapcsolas(engedelyezve: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[hangjelzesekEngedelyezveKey] = engedelyezve
        }
    }
    
    /**
     * Vibráció kapcsolása
     */
    suspend fun vibracioKapcsolas(engedelyezve: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[vibracioEngedelyezveKey] = engedelyezve
        }
    }
    
    /**
     * Hangerősség beállítása
     */
    suspend fun hangerősségBeallitas(hangerősség: Float) {
        context.dataStore.edit { preferences ->
            preferences[hangerősségKey] = hangerősség.coerceIn(0f, 1f)
        }
    }
    
    /**
     * Offline mód kapcsolása
     */
    suspend fun offlineModKapcsolas(engedelyezve: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[offlineModKey] = engedelyezve
        }
    }
    
    /**
     * Sötét téma kapcsolása
     */
    suspend fun sotetTemaKapcsolas(engedelyezve: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[sotetTemaKey] = engedelyezve
        }
    }
    
    /**
     * Duplikátum kezelés beállítása
     */
    suspend fun duplikatumKezelesBeallitas(kezeles: DuplikatumKezeles) {
        context.dataStore.edit { preferences ->
            preferences[duplikatumKezelesKey] = kezeles.ordinal
        }
    }
}