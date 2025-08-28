package com.fnagy.kartyaszkenner.funkciok.szkenneles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fnagy.kartyaszkenner.adatok.modellek.*
import com.fnagy.kartyaszkenner.adatok.repository.KartyaRepository
import com.fnagy.kartyaszkenner.core.beallitasok.BeallitasokKezelo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Szkennelési képernyő ViewModel
 */
@HiltViewModel
class SzkennelesiViewModel @Inject constructor(
    private val kartyaRepository: KartyaRepository,
    private val beallitasokKezelo: BeallitasokKezelo
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SzkennelesiUiState())
    val uiState: StateFlow<SzkennelesiUiState> = _uiState.asStateFlow()
    
    private val _ideiglenesKartyak = MutableStateFlow<List<FelismertKartya>>(emptyList())
    val ideiglenesKartyak: StateFlow<List<FelismertKartya>> = _ideiglenesKartyak.asStateFlow()
    
    val beallitasok = beallitasokKezelo.beallitasokFlow
    
    init {
        // Beállítások figyelése
        viewModelScope.launch {
            beallitasokKezelo.beallitasokFlow.collect { beallitasok ->
                _uiState.value = _uiState.value.copy(
                    beallitasok = beallitasok
                )
            }
        }
    }
    
    /**
     * Kamera állapot frissítése
     */
    fun kameraAllapotFrissites(kameraEngedelyezve: Boolean, kameraKesz: Boolean) {
        _uiState.value = _uiState.value.copy(
            kameraEngedelyezve = kameraEngedelyezve,
            kameraKesz = kameraKesz
        )
    }
    
    /**
     * Új kártya felismerés eredménye
     */
    fun ujKartyaFelismeres(felismertSzoveg: String, bizalom: Float) {
        _uiState.value = _uiState.value.copy(
            felismeresAllapot = FelismeresAllapot.FELDOLGOZAS_ALATT
        )
        
        viewModelScope.launch {
            try {
                val kartya = kartyaRepository.kartyaKeresese(felismertSzoveg)
                
                if (kartya != null && bizalom > 0.7f) {
                    val felismertKartya = FelismertKartya(
                        kartya = kartya,
                        felismeresiBizalom = bizalom,
                        felismertSzoveg = felismertSzoveg
                    )
                    
                    _uiState.value = _uiState.value.copy(
                        aktualisFelismertKartya = felismertKartya,
                        felismeresAllapot = FelismeresAllapot.SIKERES
                    )
                    
                    // Ideiglenes listához hozzáadás
                    val ujLista = _ideiglenesKartyak.value.toMutableList()
                    val meglevoIndex = ujLista.indexOfFirst { it.kartya?.id == kartya.id }
                    
                    if (meglevoIndex >= 0) {
                        // Ha már van ilyen kártya, frissítjük
                        ujLista[meglevoIndex] = felismertKartya
                    } else {
                        // Új kártya hozzáadása
                        ujLista.add(felismertKartya)
                    }
                    
                    _ideiglenesKartyak.value = ujLista
                    
                } else {
                    _uiState.value = _uiState.value.copy(
                        felismeresAllapot = if (bizalom > 0.4f) FelismeresAllapot.BIZONYTALAN else FelismeresAllapot.SIKERTELEN,
                        aktualisFelismertKartya = null
                    )
                }
                
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    felismeresAllapot = FelismeresAllapot.HIBA,
                    hibaUzenet = e.message,
                    aktualisFelismertKartya = null
                )
            }
        }
    }
    
    /**
     * Kártya hozzáadása a gyűjteményhez
     */
    fun kartyaHozzaadasaGyujtemenybe(felismertKartya: FelismertKartya) {
        felismertKartya.kartya?.let { kartya ->
            viewModelScope.launch {
                try {
                    kartyaRepository.kartyaHozzaadas(kartya)
                    
                    // Eltávolítás az ideiglenes listából
                    val ujLista = _ideiglenesKartyak.value.filterNot { 
                        it.kartya?.id == kartya.id 
                    }
                    _ideiglenesKartyak.value = ujLista
                    
                    _uiState.value = _uiState.value.copy(
                        sikeresHozzaadas = true
                    )
                    
                } catch (e: Exception) {
                    _uiState.value = _uiState.value.copy(
                        hibaUzenet = e.message
                    )
                }
            }
        }
    }
    
    /**
     * Összes ideiglenes kártya hozzáadása a gyűjteményhez
     */
    fun osszesIdeiglenesKartyaHozzaadas() {
        viewModelScope.launch {
            val ideiglenesKartyakLista = _ideiglenesKartyak.value
            var sikeresHozzaadasokSzama = 0
            
            ideiglenesKartyakLista.forEach { felismertKartya ->
                felismertKartya.kartya?.let { kartya ->
                    try {
                        kartyaRepository.kartyaHozzaadas(kartya)
                        sikeresHozzaadasokSzama++
                    } catch (e: Exception) {
                        // Hibakezelés - logolás
                    }
                }
            }
            
            // Ideiglenes lista törlése
            _ideiglenesKartyak.value = emptyList()
            
            _uiState.value = _uiState.value.copy(
                sikeresHozzaadas = sikeresHozzaadasokSzama > 0,
                aktualisFelismertKartya = null
            )
        }
    }
    
    /**
     * Ideiglenes lista törlése
     */
    fun ideiglenesListaTorlese() {
        _ideiglenesKartyak.value = emptyList()
        _uiState.value = _uiState.value.copy(
            aktualisFelismertKartya = null,
            felismeresAllapot = FelismeresAllapot.FELDOLGOZAS_ALATT
        )
    }
    
    /**
     * UI üzenetek visszaállítása
     */
    fun uiUzenetekVissaallitasa() {
        _uiState.value = _uiState.value.copy(
            hibaUzenet = null,
            sikeresHozzaadas = false
        )
    }
}

/**
 * Szkennelési képernyő UI állapot
 */
data class SzkennelesiUiState(
    val kameraEngedelyezve: Boolean = false,
    val kameraKesz: Boolean = false,
    val felismeresAllapot: FelismeresAllapot = FelismeresAllapot.FELDOLGOZAS_ALATT,
    val aktualisFelismertKartya: FelismertKartya? = null,
    val beallitasok: BeallitasokAdatok? = null,
    val hibaUzenet: String? = null,
    val sikeresHozzaadas: Boolean = false
)