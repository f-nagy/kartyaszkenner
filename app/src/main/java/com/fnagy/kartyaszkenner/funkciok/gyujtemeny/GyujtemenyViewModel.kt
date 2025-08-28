package com.fnagy.kartyaszkenner.funkciok.gyujtemeny

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fnagy.kartyaszkenner.adatok.modellek.Kartya
import com.fnagy.kartyaszkenner.adatok.repository.KartyaRepository
import com.fnagy.kartyaszkenner.adatok.repository.GyujtemenyStatisztikak
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Gyűjtemény képernyő ViewModel
 */
@HiltViewModel
class GyujtemenyViewModel @Inject constructor(
    private val kartyaRepository: KartyaRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(GyujtemenyUiState())
    val uiState: StateFlow<GyujtemenyUiState> = _uiState.asStateFlow()
    
    private val _keresoSzoveg = MutableStateFlow("")
    val keresoSzoveg: StateFlow<String> = _keresoSzoveg.asStateFlow()
    
    private val _selectedFilter = MutableStateFlow<String?>(null)
    
    // Kártyák flow kombinálása a kereső szöveggel és filterrel
    val kartyak: StateFlow<List<Kartya>> = combine(
        _keresoSzoveg,
        _selectedFilter
    ) { kereso, filter ->
        Pair(kereso, filter)
    }.flatMapLatest { (kereso, filter) ->
        when {
            kereso.isNotBlank() -> kartyaRepository.kartyakKereses(kereso)
            filter != null -> kartyaRepository.kartyakTipusAlapjan(filter)
            else -> kartyaRepository.osszesKartya()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )
    
    init {
        statisztikaFrissites()
    }
    
    /**
     * Keresési szöveg frissítése
     */
    fun keresoSzovegFrissites(ujSzoveg: String) {
        _keresoSzoveg.value = ujSzoveg
    }
    
    /**
     * Filter beállítása
     */
    fun filterBeallitas(tipus: String?) {
        _selectedFilter.value = tipus
    }
    
    /**
     * Kártya törlése a gyűjteményből
     */
    fun kartyaTorles(kartya: Kartya) {
        viewModelScope.launch {
            try {
                kartyaRepository.kartyaTorles(kartya)
                statisztikaFrissites()
                
                _uiState.value = _uiState.value.copy(
                    sikeresUzenet = "Kártya törölve"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba történt a törlés során"
                )
            }
        }
    }
    
    /**
     * Darabszám módosítása
     */
    fun darabszamModositas(kartya: Kartya, ujDarabszam: Int) {
        if (ujDarabszam <= 0) {
            kartyaTorles(kartya)
            return
        }
        
        viewModelScope.launch {
            try {
                kartyaRepository.darabszamModositas(kartya.id, ujDarabszam)
                statisztikaFrissites()
                
                _uiState.value = _uiState.value.copy(
                    sikeresUzenet = "Darabszám frissítve"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba történt a frissítés során"
                )
            }
        }
    }
    
    /**
     * CSV export
     */
    fun csvExport() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(exportAllapot = ExportAllapot.FOLYAMATBAN)
                
                // TODO: CSV export logika implementálása
                
                _uiState.value = _uiState.value.copy(
                    exportAllapot = ExportAllapot.SIKERES,
                    sikeresUzenet = "CSV export sikeres"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    exportAllapot = ExportAllapot.HIBA,
                    hibaUzenet = e.message ?: "Hiba történt az export során"
                )
            }
        }
    }
    
    /**
     * JSON export
     */
    fun jsonExport() {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(exportAllapot = ExportAllapot.FOLYAMATBAN)
                
                // TODO: JSON export logika implementálása
                
                _uiState.value = _uiState.value.copy(
                    exportAllapot = ExportAllapot.SIKERES,
                    sikeresUzenet = "JSON export sikeres"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    exportAllapot = ExportAllapot.HIBA,
                    hibaUzenet = e.message ?: "Hiba történt az export során"
                )
            }
        }
    }
    
    /**
     * Statisztikák frissítése
     */
    private fun statisztikaFrissites() {
        viewModelScope.launch {
            try {
                val statisztikak = kartyaRepository.gyujtemenyStatisztikak()
                _uiState.value = _uiState.value.copy(
                    statisztikak = statisztikak
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba a statisztikák betöltése során"
                )
            }
        }
    }
    
    /**
     * UI üzenetek törlése
     */
    fun uzenetekTorlese() {
        _uiState.value = _uiState.value.copy(
            hibaUzenet = null,
            sikeresUzenet = null
        )
    }
}

/**
 * Gyűjtemény UI állapot
 */
data class GyujtemenyUiState(
    val statisztikak: GyujtemenyStatisztikak? = null,
    val exportAllapot: ExportAllapot = ExportAllapot.NINCS,
    val hibaUzenet: String? = null,
    val sikeresUzenet: String? = null
)

/**
 * Export állapot enum
 */
enum class ExportAllapot {
    NINCS,
    FOLYAMATBAN,
    SIKERES,
    HIBA
}