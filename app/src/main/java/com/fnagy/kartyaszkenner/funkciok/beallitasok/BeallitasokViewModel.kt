package com.fnagy.kartyaszkenner.funkciok.beallitasok

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fnagy.kartyaszkenner.adatok.modellek.*
import com.fnagy.kartyaszkenner.core.beallitasok.BeallitasokKezelo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Beállítások képernyő ViewModel
 */
@HiltViewModel
class BeallitasokViewModel @Inject constructor(
    private val beallitasokKezelo: BeallitasokKezelo
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(BeallitasokUiState())
    val uiState: StateFlow<BeallitasokUiState> = _uiState.asStateFlow()
    
    val beallitasok = beallitasokKezelo.beallitasokFlow
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = BeallitasokAdatok()
        )
    
    /**
     * Felismerési sebesség változtatása
     */
    fun felismeresiSebessegValtoztatas(sebesség: FelismeresiSebesseg) {
        viewModelScope.launch {
            try {
                beallitasokKezelo.felismeresiSebessegFrissites(sebesség)
                _uiState.value = _uiState.value.copy(
                    sikeresUzenet = "Felismerési sebesség frissítve"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba a beállítás frissítése során"
                )
            }
        }
    }
    
    /**
     * Hangjelzések kapcsolása
     */
    fun hangjelzesekKapcsolas(engedelyezve: Boolean) {
        viewModelScope.launch {
            try {
                beallitasokKezelo.hangjelzesekKapcsolas(engedelyezve)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba a beállítás frissítése során"
                )
            }
        }
    }
    
    /**
     * Vibráció kapcsolása
     */
    fun vibracioKapcsolas(engedelyezve: Boolean) {
        viewModelScope.launch {
            try {
                beallitasokKezelo.vibracioKapcsolas(engedelyezve)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba a beállítás frissítése során"
                )
            }
        }
    }
    
    /**
     * Hangerősség beállítása
     */
    fun hangerősségBeallitas(hangerősség: Float) {
        viewModelScope.launch {
            try {
                beallitasokKezelo.hangerősségBeallitas(hangerősség)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba a beállítás frissítése során"
                )
            }
        }
    }
    
    /**
     * Offline mód kapcsolása
     */
    fun offlineModKapcsolas(engedelyezve: Boolean) {
        viewModelScope.launch {
            try {
                beallitasokKezelo.offlineModKapcsolas(engedelyezve)
                _uiState.value = _uiState.value.copy(
                    sikeresUzenet = if (engedelyezve) "Offline mód bekapcsolva" else "Offline mód kikapcsolva"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba a beállítás frissítése során"
                )
            }
        }
    }
    
    /**
     * Sötét téma kapcsolása
     */
    fun sotetTemaKapcsolas(engedelyezve: Boolean) {
        viewModelScope.launch {
            try {
                beallitasokKezelo.sotetTemaKapcsolas(engedelyezve)
                _uiState.value = _uiState.value.copy(
                    sikeresUzenet = if (engedelyezve) "Sötét téma bekapcsolva" else "Világos téma bekapcsolva"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba a beállítás frissítése során"
                )
            }
        }
    }
    
    /**
     * Duplikátum kezelés beállítása
     */
    fun duplikatumKezelesBeallitas(kezeles: DuplikatumKezeles) {
        viewModelScope.launch {
            try {
                beallitasokKezelo.duplikatumKezelesBeallitas(kezeles)
                _uiState.value = _uiState.value.copy(
                    sikeresUzenet = "Duplikátum kezelés frissítve"
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    hibaUzenet = e.message ?: "Hiba a beállítás frissítése során"
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
 * Beállítások UI állapot
 */
data class BeallitasokUiState(
    val hibaUzenet: String? = null,
    val sikeresUzenet: String? = null
)